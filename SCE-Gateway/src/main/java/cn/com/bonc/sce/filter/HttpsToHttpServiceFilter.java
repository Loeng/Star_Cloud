package cn.com.bonc.sce.filter;

import cn.com.bonc.sce.constant.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * 当 Gateway 开启了 HTTPS 访问控制以后，Gateway 接收到发来的 HTTPS 请求后其转发给后台的请求也会同样使用 HTTPS，如果项目
 * 中的微服务没有启用 SSL，则请求就会报错中断，故此 filter 会在 Gateway 转发 HTTPS 请求给后台微服务之前将请求转化为 HTTP
 * 请求。
 *
 * @author JoeTH
 * @version 1.1
 * @since 2019/3/20 16:39
 */
@ConditionalOnProperty( name = "sce.server.https", havingValue = "true" )
@Component
@Slf4j
public class HttpsToHttpServiceFilter implements GlobalFilter, Ordered {

    {
        log.info( MessageConstants.HTTPS_TO_HTTP_TRANSFER_FILTER_ENABLED.getMessage() );
    }

    @Override
    public Mono< Void > filter( ServerWebExchange exchange, GatewayFilterChain chain ) {
        Object uriObj = exchange.getAttributes().get( GATEWAY_REQUEST_URL_ATTR );
        if ( uriObj != null ) {
            URI uri = ( URI ) uriObj;
            uri = this.upgradeConnection( uri, "http" );
            exchange.getAttributes().put( GATEWAY_REQUEST_URL_ATTR, uri );
        }
        return chain.filter( exchange );
    }

    private URI upgradeConnection( URI uri, String scheme ) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri( uri ).scheme( scheme );
        if ( uri.getRawQuery() != null ) {
            // When building the URI, UriComponentsBuilder verify the allowed characters and does not
            // support the '+' so we replace it for its equivalent '%20'.
            // See issue https://jira.spring.io/browse/SPR-10172
            uriComponentsBuilder.replaceQuery( uri.getRawQuery().replace( "+", "%20" ) );
        }
        return uriComponentsBuilder.build( true ).toUri();
    }

    @Override
    public int getOrder() {
        return 10101;
    }
}

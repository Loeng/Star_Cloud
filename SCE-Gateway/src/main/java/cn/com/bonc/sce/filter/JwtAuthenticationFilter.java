package cn.com.bonc.sce.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/19 10:24
 */
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Override
    public GatewayFilter apply( Consumer< JwtAuthenticationFilter.Config > consumer ) {
        return null;
    }

    @Override
    public GatewayFilter apply( JwtAuthenticationFilter.Config config ) {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public ServerHttpRequest.Builder mutate( ServerHttpRequest request ) {
        return null;
    }

    @Override
    public ShortcutType shortcutType() {
        return null;
    }

    @Override
    public List< String > shortcutFieldOrder() {
        return null;
    }

    @Override
    public String shortcutFieldPrefix() {
        return null;
    }

    class Config {

    }
}

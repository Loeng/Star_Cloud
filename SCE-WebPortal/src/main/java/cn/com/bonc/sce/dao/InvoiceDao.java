package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 账号安全信息相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@FeignClient( "sce-data-mybatis" )
public interface InvoiceDao {
    @RequestMapping( value = "/invoice/info/{userId}", method = RequestMethod.GET )
    public RestRecord getInvoiceInfo( @PathVariable( "userId" ) String userId );

    @RequestMapping( value = "/invoice/address/{userId}", method = RequestMethod.GET )
    public RestRecord getInvoiceAddress( @PathVariable( "userId" ) String userId );

    @RequestMapping( value = "/invoice/info-history/{userId}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public RestRecord selectInvoiceHistory( @PathVariable( "userId" ) String userId,
                                            @PathVariable( "pageNum" ) Integer pageNum,
                                            @PathVariable( "pageSize" ) Integer pageSize,
                                            @RequestParam Map< String, Object > map
    );

    @RequestMapping( value = "/invoice/update/{userId}", method = RequestMethod.PUT )
    public RestRecord updateInvoiceInfo( @RequestBody Map< String, Object > invoiceInfo,
                                         @PathVariable( "userId" ) String userId );

    @RequestMapping( value = "/invoice/update-address/{userId}", method = RequestMethod.PUT )
    public RestRecord updateInvoiceAddress( @RequestBody Map< String, Object > address,
                                            @PathVariable( "userId" ) String userId );

    @RequestMapping( value = "/invoice/update-order-invoice-state/{userId}", method = RequestMethod.PUT )
    public RestRecord editOrderInvoiceState( @RequestBody Map< String, Object > invoiceState,
                                             @PathVariable( "userId" ) String userId );

    @RequestMapping( value = "/invoice/add-order-invoice/{userId}", method = RequestMethod.POST )
    public RestRecord addBillingInfo( @RequestBody Map< String, Object > invoiceInfo, @PathVariable( "userId" ) String userId );

    @RequestMapping( value = "/invoice/billing-by-order-no/{userId}", method = RequestMethod.POST )
    public RestRecord getBillingInfoByOrderNo( @PathVariable( "userId" ) String userId,
                                               @RequestBody List< String > orderNoList );

    @RequestMapping( value = "/invoice/billing-by-loginName", method = RequestMethod.GET )
    public RestRecord getBillingInfoByLoginName( @RequestParam("loginName") String loginName );
}

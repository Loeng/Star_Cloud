package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
public interface InvoiceMapper {

    List< Map > selectInvoiceInfoByOrganizationId( Long organizationId );

    List< Map > selectInvoiceAddress( Long organizationId );

    List< Map > selectInvoiceHistory( Map map );

    int updateInvoiceInfoByOrganizationId( Map< String, Object > invoiceInfo );

    int insertInvoiceSelective( Map< String, Object > invoiceInfo );

    Long selectOrganizationIdByUserId( String userId );

    int updateInvoiceAddressByOrganizationId( Map< String, Object > invoiceInfo );

    int insertInvoiceAddressSelective( Map< String, Object > invoiceInfo );

    @Select( "SELECT BUYING_USER_ID FROM STARCLOUDPORTAL.SCE_ORDER_INFO WHERE ORDER_ID = #{orderNO}" )
    String SelectBuyingUserByOrderNo( @Param( "orderNO" ) String orderNO );

    @Select( "SELECT ACTUAL_PAYMENT FROM STARCLOUDPORTAL.SCE_ORDER_INFO WHERE ORDER_ID = #{orderNO}" )
    BigDecimal selectActualPaymentByOrderNo( @Param( "orderNO" ) String orderNO );

    @Select( "SELECT * FROM STARCLOUDPORTAL.SCE_ORDER_INFO WHERE BUYING_USER_ID = #{BUYING_USER_ID} AND ORDER_ID not IN (SELECT ORDER_ID FROM STARCLOUDPORTAL.SCE_ORDER_INVOICE_REL )" )
    List< Map > selectValidOrderNoByingUserId( @Param( "BUYING_USER_ID" ) String BUYING_USER_ID );


    @Select( "SELECT ORDER_INVOICE_ID FROM STARCLOUDPORTAL.SCE_ORDER_INVOICE_REL WHERE ORDER_ID = #{orderNO}" )
    String selectInvoiceIdByOrderNo( @Param( "orderNO" ) String orderNO );

    Map selectOrderInvoiceInfo( @Param( "ID" ) String ID );

    int insertOrderInvoiceRel( @Param( "ID" ) long id, @Param( "ORDER_ID" ) String orderId, @Param( "ORDER_INVOICE_ID" ) String orderInvoiceId );

    int insertOrderInvoiceSelective( Map map );

    int updateOrderInvoiceSelective( Map map );


}

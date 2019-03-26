package cn.com.bonc.sce.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
public interface InvoiceMapper {

    List< Map > selectInvoiceInfoByOrganizationId( Long organizationId );

    List< Map > selectInvoiceAddress( Long organizationId );

    List< Map > selectInvoiceHistory( String userId );

    int updateInvoiceInfoByOrganizationId( Map< String, Object > invoiceInfo );

    int insertInvoiceSelective( Map< String, Object > invoiceInfo );

    Long selectOrganizationIdByUserId( String userId );

    int updateInvoiceAddressByOrganizationId( Map< String, Object > invoiceInfo );

    int insertInvoiceAddressSelective( Map< String, Object > invoiceInfo );

}

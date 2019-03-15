package cn.com.bonc.sce.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
public interface InvoiceMapper {

    List< Map > selectInvoiceInfoByOrganizationId( Long organizationId );

    int updateInvoiceInfoByOrganizationId( Map< String, Object > invoiceInfo );

    Long selectOrganizationIdByUserId( String userId );
}

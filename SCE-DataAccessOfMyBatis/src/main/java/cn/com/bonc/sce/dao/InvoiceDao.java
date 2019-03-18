package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InvoiceDao {
    @Autowired
    private InvoiceMapper invoiceMapper;

    public List< Map > selectInvoiceInfoByOrganizationId( Long organizationId ) {
        return invoiceMapper.selectInvoiceInfoByOrganizationId( organizationId );
    }

    public List< Map > selectInvoiceHistory( String userId ) {
        return invoiceMapper.selectInvoiceHistory( userId );
    }


    public int updateInvoiceInfoByOrganizationId( Map< String, Object > invoiceInfo ) {
        return invoiceMapper.updateInvoiceInfoByOrganizationId( invoiceInfo );
    }

    public Long selectOrganizationIdByUserId( String userId ) {
        return invoiceMapper.selectOrganizationIdByUserId( userId );
    }
}

package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.InvoiceDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jc_D
 * @description
 * @date 2019/3/15
 **/
@Slf4j
@Service
public class InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;

    public RestRecord selectInvoiceInfoByOrganizationId( String userId ) {
        return invoiceDao.getInvoiceInfo( userId );
    }

    public RestRecord selectInvoiceAddressByOrganizationId( String userId ) {
        return invoiceDao.getInvoiceAddress( userId );
    }

    public RestRecord updateInvoiceInfoByOrganizationId( Map< String, Object > invoiceInfo, String userId ) {
        return invoiceDao.updateInvoiceInfo( invoiceInfo, userId );
    }

    public RestRecord updateInvoiceAddressByOrganizationId( Map< String, Object > invoiceInfo, String userId ) {
        return invoiceDao.updateInvoiceAddress( invoiceInfo, userId );
    }

    public RestRecord selectInvoiceHistory( String userId, Integer pageNum, Integer pageSize, Map map ) {
        return invoiceDao.selectInvoiceHistory( userId, pageNum, pageSize, map );
    }

    public RestRecord editOrderInvoiceState( Map map, String userId ) {
        return invoiceDao.editOrderInvoiceState( map, userId );
    }

    public RestRecord addBillingInfo( Map< String, Object > invoiceInfo, String userId ) {
        return invoiceDao.addBillingInfo( invoiceInfo, userId );
    }

    public RestRecord getBillingInfoByOrderNo( String userId, List< String > orderNoList ) {
        return invoiceDao.getBillingInfoByOrderNo( userId, orderNoList );
    }

    public RestRecord getBillingInfoByLoginName( String loginName ) {
        return invoiceDao.getBillingInfoByLoginName(loginName);
    }
}

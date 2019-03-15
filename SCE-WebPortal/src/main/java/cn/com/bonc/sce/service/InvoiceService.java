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

    public RestRecord updateInvoiceInfoByOrganizationId( Map< String, Object > invoiceInfo, String userId ) {
        return invoiceDao.updateInvoiceInfo( invoiceInfo, userId );
    }
}

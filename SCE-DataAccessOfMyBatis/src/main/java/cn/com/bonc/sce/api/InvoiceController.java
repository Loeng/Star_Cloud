package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.InvoiceDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping( "/invoice" )
public class InvoiceController {

    @Autowired
    private InvoiceDao invoiceDao;


    @GetMapping( "/info/{userId}" )
    @ResponseBody
    public RestRecord getInvoiceInfo( @PathVariable( "userId" ) String userId ) {
        Long ORGANIZATION_ID = invoiceDao.selectOrganizationIdByUserId( userId );
        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        } else {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceDao.selectInvoiceInfoByOrganizationId( ORGANIZATION_ID ) );
        }
    }

    @PutMapping( "/update/{userId}" )
    @ResponseBody
    public RestRecord updateInvoiceInfo( @RequestBody Map< String, Object > invoiceInfo, @PathVariable( "userId" ) String userId ) {
        Long ORGANIZATION_ID = invoiceDao.selectOrganizationIdByUserId( userId );
        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        } else {
            invoiceInfo.put( "ORGANIZATION_ID", ORGANIZATION_ID );
            invoiceInfo.put( "userId", userId );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceDao.updateInvoiceInfoByOrganizationId( invoiceInfo ) );
        }

    }
}

package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.InvoiceDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 发票管理相关接口
 */
@Slf4j
@RestController
@RequestMapping( "/invoice" )
public class InvoiceController {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询发票基础信息
     *
     * @param userId
     * @return
     */
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

    /**
     * 开票列表
     *
     * @param userId
     * @return
     */
    @GetMapping( "/info-history/{userId}/{pageNum}/{pageSize}" )
    public RestRecord selectInvoiceHistory( @PathVariable( "userId" ) String userId,
                                            @PathVariable( "pageNum" ) Integer pageNum,
                                            @PathVariable( "pageSize" ) Integer pageSize,
                                            @RequestParam Map< String, Object > map ) {
        PageHelper.startPage( pageNum, pageSize );
        List< Map > data = invoiceDao.selectInvoiceHistory( map );
        PageInfo pageInfo = new PageInfo( data );

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, pageInfo );
    }

    /**
     * 修改发票基础信息
     *
     * @param invoiceInfo
     * @param userId
     * @return
     */
    @PutMapping( "/update/{userId}" )
    @ResponseBody
    public RestRecord updateInvoiceInfo( @RequestBody Map< String, Object > invoiceInfo, @PathVariable( "userId" ) String userId ) {
        Long ORGANIZATION_ID = invoiceDao.selectOrganizationIdByUserId( userId );
        //1.如果没有组织id，肯定不是学校用户
        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        }

        invoiceInfo.put( "ORGANIZATION_ID", ORGANIZATION_ID );
        invoiceInfo.put( "UPDATE_USER_ID", userId );
        invoiceInfo.put( "IS_DELETE", 1 );

        //2.查看是否已有发票
        List< Map > invoice = invoiceDao.selectInvoiceInfoByOrganizationId( ORGANIZATION_ID );
        if ( CollectionUtil.isEmpty( invoice ) ) {

            invoiceInfo.put( "CREATE_TIME", new Date() );
            invoiceInfo.put( "ID", idWorker.nextId() );
            //2.1没有发票资质，创建新的发票
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceDao.insertInvoiceSelective( invoiceInfo ) );
        } else {
            //2.2有发票资质就直接修改
            invoiceInfo.put( "UPDATE_TIME", new Date() );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceDao.updateInvoiceInfoByOrganizationId( invoiceInfo ) );
        }
    }

    /**
     * 查询发票邮寄地址
     *
     * @param userId
     * @return
     */
    @GetMapping( "/address/{userId}" )
    public RestRecord getInvoiceAddress( @PathVariable( "userId" ) String userId ) {
        Long ORGANIZATION_ID = invoiceDao.selectOrganizationIdByUserId( userId );
        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        } else {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceDao.selectInvoiceAddress( ORGANIZATION_ID ) );
        }
    }


    /**
     * 修改收票地址信息
     *
     * @param address
     * @return
     */
    @PutMapping( "/update-address/{userId}" )
    public RestRecord updateInvoiceAddressByOrganizationId( @RequestBody Map< String, Object > address, @PathVariable( "userId" ) String userId ) {
        Long ORGANIZATION_ID = invoiceDao.selectOrganizationIdByUserId( userId );
        //1.如果没有组织id，肯定不是学校用户
        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        }
        address.put( "ORGANIZATION_ID", ORGANIZATION_ID );
        address.put( "IS_DELETE", 1 );

        //2.查看是否已有发票
        List< Map > invoice = invoiceDao.selectInvoiceAddress( ORGANIZATION_ID );
        if ( CollectionUtil.isEmpty( invoice ) ) {
            address.put( "ID", idWorker.nextId() );
            //2.1没有收票地址，创建新的地址
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceDao.insertInvoiceAddressSelective( address ) );
        } else {
            //2.2有收票地址就直接修改
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceDao.updateInvoiceAddressByOrganizationId( address ) );
        }
    }

}

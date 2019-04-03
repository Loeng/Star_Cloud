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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

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

    //新增发票的时候，根据订单号查询 开票信息
    @PostMapping( "/billing-by-order-no/{userId}" )
    public RestRecord getBillingInfoByOrderNo(
            @PathVariable( "userId" ) String userId,
            @RequestBody List< String > orderNoList
    ) {
        //用户填写了多个订单号
        List< Object > list = new ArrayList<>();
        Set< String > buyingUserIdList = new HashSet<>();
        for ( String orderNO : orderNoList ) {
            Map m = new HashMap();
            list.add( m );

            String invoiceId = invoiceDao.selectInvoiceIdByOrderNo( orderNO );
            if ( invoiceId != null ) {
                //【订单发票关系表】中存在该订单号，不能再对该订单进行新增开票操作。
                m.put( "orderNO", orderNO );
                m.put( "msg", WebMessageConstants.SCE_PORTAL_MSG_480 );
                return new RestRecord( 483, WebMessageConstants.SCE_PORTAL_MSG_483, list );
            }
            // Map OrderInvoiceInfo = invoiceDao.selectOrderInvoiceInfo( invoiceId );
            //【订单发票关系表】中不存在该订单号，查询订单表是否有该订单号
            String BuyingUserId = invoiceDao.SelectBuyingUserByOrderNo( orderNO );
            buyingUserIdList.add( BuyingUserId );
            if ( StringUtils.isEmpty( BuyingUserId ) ) {
                //查不到订单号
                m.put( "orderNO", orderNO );
                m.put( "msg", WebMessageConstants.SCE_PORTAL_MSG_481 );
                return new RestRecord( 483, WebMessageConstants.SCE_PORTAL_MSG_483, list );
            }
        }
        if ( buyingUserIdList.size() == 1 ) {
            //是同一用户的订单
            Long ORGANIZATION_ID = invoiceDao.selectOrganizationIdByUserId( buyingUserIdList.iterator().next() );
            List< Map > address = invoiceDao.selectInvoiceAddress( ORGANIZATION_ID );
            List< Map > invoiceInfo = invoiceDao.selectInvoiceInfoByOrganizationId( ORGANIZATION_ID );
            Map temp = new HashMap<>();
            temp.put( "address", address );
            temp.put( "invoiceInfo", invoiceInfo );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );
        }
        return new RestRecord( 483, WebMessageConstants.SCE_PORTAL_MSG_483, list );
    }


    /**
     * 增开发票(订单号用逗号分隔)
     *
     * @param userId
     * @param map
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    @PostMapping( "/add-order-invoice/{userId}" )
    public RestRecord addBillingInfo(
            @PathVariable( "userId" ) String userId,
            @RequestBody Map map ) {

        String orderNOs = ( String ) map.get( "ORDER_NO" );
        if ( orderNOs == null ) {
            return new RestRecord( 482, WebMessageConstants.SCE_PORTAL_MSG_482 );
        }
        String[] orderNOArr = orderNOs.split( "," );
        //根据订单号查询金额
        //将所有订单实际支付金额累加
        BigDecimal money = new BigDecimal( "0" );
        for ( String order : orderNOArr ) {
            money = money.add( invoiceDao.selectActualPaymentByOrderNo( order ) );
        }
        //生成一个订单发票id
        long orderInvoiceId = idWorker.nextId();
        //存发票表
        map.put( "INVOICE_ACCOUNT", money );
        map.put( "APPLY_TIME", new Date() );
        map.put( "ID", orderInvoiceId );
        invoiceDao.insertOrderInvoiceSelective( map );

        //存入订单与发票关系表
        for ( String order : orderNOArr ) {
            invoiceDao.insertOrderInvoiceRel( idWorker.nextId(), order, String.valueOf( orderInvoiceId ) );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }


    /**
     * 编辑发票状态信息
     *
     * @param userId
     * @param map
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    @PostMapping( "/update-order-invoice-state/{userId}" )
    public RestRecord editOrderInvoiceState(
            @PathVariable( "userId" ) String userId,
            @RequestBody Map map ) {

        //  1未开票，2已开票，3已邮寄
        Map param = new HashMap();
        param.put( "ID", map.get( "ID" ) );
        param.put( "INVOICE_CODE", map.get( "INVOICE_CODE" ) );
        param.put( "INVOICE_NO", map.get( "INVOICE_NO" ) );
        param.put( "EXPRESS_COMPANY", map.get( "EXPRESS_COMPANY" ) );
        param.put( "COURIER_NUMBER", map.get( "COURIER_NUMBER" ) );
        param.put( "INVOICE_STATUS", map.get( "INVOICE_STATUS" ) );
        //todo
        // 已邮寄就不能修改
        return new RestRecord( 200, invoiceDao.updateOrderInvoiceSelective( param ) );
    }


}

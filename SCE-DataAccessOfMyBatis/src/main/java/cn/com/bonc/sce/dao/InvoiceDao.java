package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.InvoiceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class InvoiceDao {
    @Autowired
    private InvoiceMapper invoiceMapper;

    /**
     * 查询发票资质信息
     *
     * @param organizationId
     * @return
     */
    public List< Map > selectInvoiceInfoByOrganizationId( Long organizationId ) {
        return invoiceMapper.selectInvoiceInfoByOrganizationId( organizationId );
    }

    /**
     * 查询开票历史记录
     *
     * @param map
     * @return
     */
    public List< Map > selectInvoiceHistory( Map map ) {
        return invoiceMapper.selectInvoiceHistory( map );
    }

    /**
     * 修改发票资质信息
     *
     * @param invoiceInfo
     * @return
     */
    public int updateInvoiceInfoByOrganizationId( Map< String, Object > invoiceInfo ) {
        return invoiceMapper.updateInvoiceInfoByOrganizationId( invoiceInfo );
    }

    /**
     * 新增发票资质信息
     *
     * @param invoiceInfo
     * @return
     */
    public int insertInvoiceSelective( Map< String, Object > invoiceInfo ) {
        return invoiceMapper.insertInvoiceSelective( invoiceInfo );
    }


    /**
     * 根据userId查询组织Id
     *
     * @param userId
     * @return
     */
    public Long selectOrganizationIdByUserId( String userId ) {
        return invoiceMapper.selectOrganizationIdByUserId( userId );
    }


    /**
     * 查询收票地址信息
     *
     * @param organizationId
     * @return
     */
    public List< Map > selectInvoiceAddress( Long organizationId ) {
        return invoiceMapper.selectInvoiceAddress( organizationId );
    }


    /**
     * 修改收票地址信息
     *
     * @param invoiceInfo
     * @return
     */
    public int updateInvoiceAddressByOrganizationId( Map< String, Object > invoiceInfo ) {
        return invoiceMapper.updateInvoiceAddressByOrganizationId( invoiceInfo );
    }

    /**
     * 新增收票地址信息
     *
     * @param invoiceInfo
     * @return
     */
    public int insertInvoiceAddressSelective( Map< String, Object > invoiceInfo ) {
        return invoiceMapper.insertInvoiceAddressSelective( invoiceInfo );
    }

    /**
     * 根据订单号查询购买人userID
     *
     * @param orderNO
     * @return
     */
    public String SelectBuyingUserByOrderNo( String orderNO ) {
        return invoiceMapper.SelectBuyingUserByOrderNo( orderNO );
    }

    /**
     * 根据订单号查询实际支付金额
     *
     * @param orderNO
     * @return
     */
    public BigDecimal selectActualPaymentByOrderNo( String orderNO ) {
        return invoiceMapper.selectActualPaymentByOrderNo( orderNO );
    }

    /**
     * 根据userId 查询用户的订单号
     *
     * @param byingUserId
     * @return
     */
    public List< Map > selectValidOrderNoByingUserId( String byingUserId ) {
        return invoiceMapper.selectValidOrderNoByingUserId( byingUserId );
    }

    /**
     * 判断订单号是否在订单与开票信息关系表中
     *
     * @param orderNO
     * @return
     */
    public Boolean isExistRel( String orderNO ) {
        return invoiceMapper.selectInvoiceIdByOrderNo( orderNO ) != null;
    }

    /**
     * 通过订单号查询订单与开票信息关系表中的发票信息id
     *
     * @param orderNO
     * @return
     */
    public String selectInvoiceIdByOrderNo( String orderNO ) {
        return invoiceMapper.selectInvoiceIdByOrderNo( orderNO );
    }

    /**
     * 查询开票信息
     *
     * @param ID
     * @return
     */
    public Map selectOrderInvoiceInfo( String ID ) {
        return invoiceMapper.selectOrderInvoiceInfo( ID );
    }

    /**
     * 插入订单发票关系表
     */

    public int insertOrderInvoiceRel( long id, String orderId, String orderInvoiceId ) {
        return invoiceMapper.insertOrderInvoiceRel( id, orderId, orderInvoiceId );
    }

    /**
     * 插入订单发票表
     */

    public int insertOrderInvoiceSelective( Map map ) {
        return invoiceMapper.insertOrderInvoiceSelective( map );
    }

    /**
     * 通过id修改订单发票信息
     */
    public int updateOrderInvoiceSelective( Map map ) {
        return invoiceMapper.updateOrderInvoiceSelective( map );
    }


}

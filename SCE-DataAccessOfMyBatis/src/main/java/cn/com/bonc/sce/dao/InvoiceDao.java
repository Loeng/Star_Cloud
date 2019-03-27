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
}

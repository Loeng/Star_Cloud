package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.CompanyInfoDao;
import cn.com.bonc.sce.model.CompanyInfo;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping( "/Company" )
public class CompanyInfoApiController {

    private CompanyInfoDao companyInfoDao;

    @Autowired
    public CompanyInfoApiController( CompanyInfoDao companyInfoDao ) {
        this.companyInfoDao = companyInfoDao;
    }

    /**
     * 查询全部商家信息
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    public RestRecord selectAllCompanyList(
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return new RestRecord( 0, companyInfoDao.selectAllCompanyList() );
    }


    /**
     * 根据输入名字模糊搜索厂商信息
     *
     * @param companyName 用户输入搜索厂商名
     * @return 返回查询结果
     */
    @GetMapping( "/{companyName}" )
    @ResponseBody
    public RestRecord selectCompanyListByName(
            @PathVariable( value = "companyName" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return new RestRecord( 0, companyInfoDao.selectCompanyListByName( companyName ) );
    }


    /**
     * 根据厂商ID查询厂商信息
     *
     * @param companyId 搜索的厂商ID
     * @return 返回查询结果
     */
    @GetMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord selectCompanyById(
            @PathVariable( "companyId" ) String companyId,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return new RestRecord( 0, companyInfoDao.selectCompanyById( companyId ) );
    }

    /**
     * 添加单个厂商信息
     *
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否添加成功
     */
    @PutMapping
    @ResponseBody
    public RestRecord addCompanyInfo( @RequestBody CompanyInfo companyInfo ) {
        return new RestRecord( 0, companyInfoDao.addCompanyInfo( companyInfo ) );
    }

    /**
     * 根据用户输入信息，在厂商信息表中修改对应厂商信息
     *
     * @param companyId   所需更新的厂商ID
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否更新成功
     */
    @PatchMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord updateCompanyInfo( @PathVariable( "companyId" ) String companyId,
                                         @RequestBody CompanyInfo companyInfo ) {
        return new RestRecord( 0, companyInfoDao.updateCompanyInfo( companyId, companyInfo ) );
    }

    /**
     * 删除单个厂商信息
     * 在厂商信息表中将对应厂商状态改为已删除
     *
     * @param companyId 要删除的厂商ID
     * @return 返回是否删除成功
     */
    @DeleteMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord deleteCompanyInfo( @PathVariable( "companyId" ) String companyId ) {
        return new RestRecord( 0, companyInfoDao.deleteCompanyInfo( companyId ) );
    }
}

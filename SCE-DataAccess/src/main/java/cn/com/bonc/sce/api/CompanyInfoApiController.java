package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.CompanyInfoRepository;
import cn.com.bonc.sce.entity.CompanyInfo;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping( "/company" )
public class CompanyInfoApiController {

    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    public CompanyInfoApiController( CompanyInfoRepository companyInfoRepository ) {
        this.companyInfoRepository = companyInfoRepository;
    }

    @GetMapping
    @ResponseBody
    public RestRecord queryCompanyInfo(
            @RequestParam( value = "companyId", required = false ) String companyId,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize ) {

        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        RestRecord restRecord = new RestRecord(200);
//        restRecord.setData( companyInfoRepository.queryCompanyInfo( companyId, companyName, pageable ) );
        //虚假返回数据
        List< Map< String, Object > > resultList = new ArrayList<>();
        Map< String, Object > map1 = new HashMap<>();
        map1.put( "companyName", "公司名称1" );
        map1.put( "userId", "cs_001" );
        map1.put( "loginPermissionStatus", 1 );
        map1.put( "downloadCount", 256 );
        map1.put( "companyStatus", 1 );
        Map< String, Object > map2 = new HashMap<>();
        map2.put( "companyName", "公司名称2" );
        map2.put( "userId", "cs_001" );
        map2.put( "loginPermissionStatus", 1 );
        map2.put( "downloadCount", 365 );
        map2.put( "companyStatus", 1 );
        resultList.add( map1 );
        resultList.add( map2 );
        Map< String, Object > resultMap = new HashMap<>();
        resultMap.put( "data", resultList );
        resultMap.put( "totalCount", 32 );
        resultMap.put( "totalPage", 4 );
        restRecord.setData( resultMap );
        return restRecord;
    }


    /**
     * 添加单个厂商信息
     *
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否添加成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord addCompanyInfo(
            @RequestBody CompanyInfo companyInfo ) {
        RestRecord restRecord = new RestRecord(200);
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
        restRecord.setData( resultMap );
        return restRecord;
//        return new RestRecord( 0, companyInfoRepository.addCompanyInfo( companyInfo ) );
    }

    /**
     * 根据用户输入信息，在厂商信息表中修改对应厂商信息
     *
     * @param companyId   所需更新的厂商ID
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否更新成功
     */
    @PutMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord updateCompanyInfo(
            @PathVariable( "companyId" ) String companyId,
            @RequestBody CompanyInfo companyInfo ) {
        RestRecord restRecord = new RestRecord(200);
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
        restRecord.setData( resultMap );
        return restRecord;
//        return new RestRecord( 0, companyInfoRepository.updateCompanyInfo( companyId, companyInfo ) );
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
    public RestRecord deleteCompanyInfo(
            @PathVariable( "companyId" ) String companyId ) {
        RestRecord restRecord = new RestRecord(200);
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
        restRecord.setData( resultMap );
        return restRecord;
//        return new RestRecord( 0, companyInfoRepository.deleteCompanyInfo( companyId ) );
    }
}

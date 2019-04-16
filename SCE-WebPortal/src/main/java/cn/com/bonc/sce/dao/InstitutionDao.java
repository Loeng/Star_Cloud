package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Institution;
import cn.com.bonc.sce.model.InstitutionInfo;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019/4/14.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface InstitutionDao {

    @RequestMapping( value = "/institution/editInstitutionInfo", method = RequestMethod.PUT )
    RestRecord editInstitutionInfo(@RequestBody @ApiParam( "教育局人员从业信息对象" ) InstitutionInfo info);

    @RequestMapping(value = "/institution/getInstitutionInfoByUserId/{userId}", method = RequestMethod.GET)
    RestRecord getInstitutionInfoByUserId(@PathVariable("userId") String userId);

    @RequestMapping( value = "/institution/addInstitutionInfo", method = RequestMethod.POST )
    RestRecord addInstitutionInfo(@RequestBody  @ApiParam( "教育局人员从业信息对象" ) InstitutionInfo info);

    @RequestMapping( value = "/institution/addInstitution/{roleId}", method = RequestMethod.POST )
    RestRecord addInstitution(Institution institution, @RequestParam( "userId" ) String userId, @PathVariable( "roleId" ) Integer roleId);

    @RequestMapping( value = "/institution/updateInstitutionById", method = RequestMethod.PUT )
    RestRecord updateInstitutionById(@RequestBody @ApiParam( "教育局信息对象" ) Institution institution);

    @RequestMapping(value = "/institution/{id}", method = RequestMethod.GET)
    RestRecord getInstitutionById(@PathVariable("id") String id);

    @RequestMapping( value = "/institution/updateInstitutionInfo", method = RequestMethod.PUT )
    RestRecord updateInstitutionInfo(@RequestBody @ApiParam( "教育局信息对象" ) Institution institution);

    @RequestMapping( value = "/institution/getInstitutionInfoList/{INSTITUTION_NAME}/{AUDIT_STATUS}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getInstitutionInfoList(@PathVariable("INSTITUTION_NAME") String INSTITUTION_NAME,@PathVariable("AUDIT_STATUS") String AUDIT_STATUS
            ,@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize);


}

package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.model.InfoTeacherModel;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/8.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface UserManagerDao {

    @RequestMapping( value = "/userManager/delUser", method = RequestMethod.DELETE )
    RestRecord delUser(@RequestParam("id")String id);

    @RequestMapping( value = "/userManager/resetPwd", method = RequestMethod.PUT )
    RestRecord resetPwd(@RequestParam("id") String id);

    @RequestMapping( value = "/userManager/editLogin", method = RequestMethod.PUT )
    RestRecord editLogin(@RequestParam("id")String id, @RequestParam("loginPermissionStatus")Integer loginPermissionStatus);

    @RequestMapping( value = "/userManager/getSchools4edu/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getSchools4edu(@RequestParam("id") long id, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize);

    @RequestMapping( value = "/userManager/delSchools4edu", method = RequestMethod.DELETE )
    RestRecord delSchools4edu(@RequestParam("id") long id, @RequestParam("institutionId") long institutionId);

    @RequestMapping( value = "/userManager/getInstitutionList/{pageNum}/{pageSize}", method = RequestMethod.POST )
    RestRecord getInstitutionList(@RequestBody String json,@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize);

    @RequestMapping( value = "/userManager/getInstitutions", method = RequestMethod.GET )
    List<Map> getInstitutions(@RequestParam("id")String id, @RequestParam("institutionName")String institutionName, @RequestParam("loginPermissionStatus") String loginPermissionStatus);

    @RequestMapping( value = "/userManager/register", method = RequestMethod.POST )
    RestRecord register(@RequestBody String json);

    @RequestMapping( value = "/userManager/getPhone", method = RequestMethod.GET )
    RestRecord getPhone(@RequestParam("loginName") String loginName);

    @RequestMapping( value = "/userManager/updatePwdByName", method = RequestMethod.PUT )
    RestRecord updatePwdByName(@RequestParam("loginName") String loginName, @RequestParam("password") String password);

    @RequestMapping( value = "/userManager/testCertificate", method = RequestMethod.GET )
    RestRecord testCertificate(@RequestParam("loginName") String loginName, @RequestParam("certificate") String certificate);

    @RequestMapping( value = "/userManager/getTeachers/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getTeachers(@RequestParam("organizationId")long organizationId,
                           @RequestParam("userName") String userName,
                           @RequestParam("loginName")String loginName,
                           @RequestParam("gender")String gender,
                           @RequestParam("position")String position,
                           @RequestParam("accountStatus")String accountStatus,
                           @PathVariable("pageNum")Integer pageNum,
                           @PathVariable("pageSize")Integer pageSize);

    @RequestMapping( value = "/userManager/delTeacher", method = RequestMethod.DELETE )
    RestRecord delTeacher( @RequestParam("id")String id);

    @RequestMapping( value = "/userManager/getTeacherInfo", method = RequestMethod.GET )
    RestRecord getTeacherInfo(@RequestParam("id") String id);

    @RequestMapping( value = "/userManager/editTeacherInfo", method = RequestMethod.PUT )
    RestRecord editTeacherInfo(@RequestBody String json);

    @RequestMapping( value = "/userManager/addTeacher", method = RequestMethod.POST )
    RestRecord addTeacher(@RequestBody String json);

    @RequestMapping( value = "/userManager/getTransferTeachers/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getTransferTeachers(@RequestParam("getType")Integer getType, @RequestParam("organizationId")long organizationId,
                                   @RequestParam("userName")String userName, @RequestParam("loginName")String loginName,
                                   @RequestParam("gender")String gender, @RequestParam("position")String position,
                                   @RequestParam("accountStatus")Integer accountStatus, @PathVariable("pageNum")Integer pageNum,
                                   @PathVariable("pageSize")Integer pageSize);

    @RequestMapping( value = "/userManager/getStudents/{pageNum}/{pageSize}", method = RequestMethod.GET)
    RestRecord getStudents( @RequestParam( "userName" ) String userName,
                            @RequestParam( "loginName" ) String loginName,
                            @RequestParam( "studentNumber" ) String studentNumber,
                            @RequestParam( "gender" ) String gender,
                            @RequestParam( "grade" ) String grade,
                            @RequestParam( "accountStatus" ) String accountStatus,
                            @RequestParam( "userId" ) String userId,
                            @PathVariable( "pageNum" ) String pageNum,
                            @PathVariable( "pageSize" ) String pageSize);

    @RequestMapping( value = "/userManager/getStudentInfo/{userId}", method = RequestMethod.GET )
    RestRecord getStudentInfo( @PathVariable("userId") String userId );

    @RequestMapping( value = "/userManager/editStudent", method = RequestMethod.PUT )
    RestRecord editStudent(@RequestBody Map map);

    @RequestMapping( value = "/userManager/delStudent", method = RequestMethod.DELETE )
    RestRecord delStudent(@RequestParam( "userId" ) String userId);

    @RequestMapping( value = "/userManager/addStudent", method = RequestMethod.POST )
    RestRecord addStudent(@RequestBody Map map, @RequestParam( "userId" ) String userId);

    @RequestMapping( value = "/userManager/editTeacherPracticeInfo", method = RequestMethod.PUT )
    RestRecord editTeacherPracticeInfo(@RequestBody  @ApiParam( "教师从业信息对象" ) InfoTeacherModel model);

    @RequestMapping( value = "/userManager/getParentInfo/{certificationType}/{certificationNumber}/{userType}", method = RequestMethod.GET)
    RestRecord getParentInfo(@PathVariable("certificationType") String certificationType,
                             @PathVariable("certificationNumber") String certificationNumber,
                             @PathVariable("userType") String userType);

    @RequestMapping( value = "/userManager/getTransferStudent/{pageNum}/{pageSize}", method = RequestMethod.GET)
    RestRecord getTransferStudent(@RequestParam( value = "userName", required = false ) String userName,
                                  @RequestParam( value = "loginName", required = false ) String loginName,
                                  @RequestParam( value = "studentNumber", required = false ) String studentNumber,
                                  @RequestParam( value = "gender", required = false ) String gender,
                                  @RequestParam( value = "grade", required = false ) String grade,
                                  @RequestParam( value = "applyStatus", required = false ) String applyStatus,
                                  @RequestParam( value = "transferType" ) String transferType,
                                  @PathVariable("pageNum") String pageNum,
                                  @PathVariable("pageSize") String pageSize,
                                  @RequestParam( value = "userId") String userId);

    @RequestMapping( value = "/userManager/repealApply", method = RequestMethod.DELETE )
    RestRecord repealApply(@RequestParam("userId") String userId,
                           @RequestBody Map map);

    @RequestMapping( value = "/userManager/reCall/{transferId}", method = RequestMethod.PUT )
    RestRecord reCall(@PathVariable("transferId") String transferId);

    @RequestMapping( value = "/userManager/getTransferOut/{transferId}", method = RequestMethod.GET )
    RestRecord getTransferOut(@PathVariable("transferId") String transferId);

    @RequestMapping( value = "/userManager/auditTransfer", method = RequestMethod.PUT )
    RestRecord auditTransfer(@RequestParam("userId") String userId,
                             @RequestBody Map map);

    @RequestMapping(value = "/userManager/getTeacherInfoById/{userId}", method = RequestMethod.GET)
    RestRecord getTeacherInfoById(@PathVariable("userId") String userId);

    @RequestMapping( value = "/userManager/addTeacherInfo", method = RequestMethod.POST )
    RestRecord addTeacherInfo(@RequestBody  @ApiParam( "教师从业信息对象" ) InfoTeacherModel model);

    @RequestMapping(value = "/userManager/{userId}", method = RequestMethod.GET)
    RestRecord getUserById(@PathVariable("userId") String userId);

}
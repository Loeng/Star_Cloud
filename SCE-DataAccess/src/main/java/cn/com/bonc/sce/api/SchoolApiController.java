package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.School;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.SchoolService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@RestController
@RequestMapping("/schools")
public class SchoolApiController {
    @Autowired
    private SchoolService schoolService;
    @PersistenceContext
    private EntityManager entityManager;
//
//    /**
//     * 添加school
//     *
//     * @param school 信息
//     * @return 是否添加成功
//     */
//    @PostMapping( "" )
//    @ResponseBody
//    public RestRecord insertSchool( @RequestBody School school ) {
//        try {
//            return schoolService.insertSchool( school );
//        } catch ( Exception e ) {
//            log.error( e.getMessage(), e );
//            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
//        }
//    }

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    @GetMapping("")
    @ResponseBody
    public RestRecord getAll(@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        try {
            return schoolService.getAll(pageNum, pageSize);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new RestRecord(406, MessageConstants.SCE_MSG_406, e);
        }
    }


    /**
     * 新增学校实体
     *
     * @return 新增学校实体
     */
    @ApiOperation(value = "新增学校实体", notes = "新增学校实体", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class),
            @ApiResponse(code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class)
    })
    @PostMapping
    @ResponseBody
    public RestRecord saveSchool(@RequestBody Map map) {
        return schoolService.saveSchool(map);
    }


    @ApiOperation(value = "新增学校信息接口", notes = "新增学校信息", httpMethod = "POST")
    @PostMapping("/addSchool/{roleId}")
    @ResponseBody
    public RestRecord addSchool(@RequestBody School school, @RequestParam("userId") String userId, @PathVariable("roleId") Integer roleId) {
        return schoolService.addSchool(school, userId, roleId);
    }

    @ApiOperation(value = "通过学校id获取学校信息接口", notes = "通过学校id获取学校信息", httpMethod = "GET")
    @GetMapping("/{id}")
    @ResponseBody
    public RestRecord getSchoolById(@PathVariable("id") Integer id) {
        Optional<School> school = schoolService.getSchoolById(id);
        if (school == null) {
            return new RestRecord(1010, MessageConstants.SCE_MSG_1010, id);
        } else {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, school);
        }
    }

    @ApiOperation(value = "通过学校id修改学校信息接口", notes = "通过学校id修改学校信息", httpMethod = "PUT")
    @PutMapping("/updateSchoolById")
    @ResponseBody
    public RestRecord updateSchoolById(@RequestBody @ApiParam("学校信息对象") School school) {
        return schoolService.updateSchoolById(school);
    }

    @ApiOperation(value = "变更或驳回提交学校信息接口", notes = "变更或驳回提交学校信息", httpMethod = "PUT")
    @PutMapping("/updateSchoolInfo")
    @ResponseBody
    public RestRecord updateSchoolInfo(@RequestBody @ApiParam("学校信息对象") School school, @ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam("userId") String userId, @ApiParam(name = "roleId", value = "角色类型", required = true) @RequestParam("roleId") Integer roleId) {
        return schoolService.updateSchoolInfo(school, userId, roleId);
    }

    @GetMapping("/getSchoolInfoList/{SCHOOL_NAME}/{SCHOOL_TYPE}/{AUDIT_STATUS}/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getSchoolInfoList(@PathVariable("SCHOOL_NAME") String SCHOOL_NAME, @PathVariable("SCHOOL_TYPE") String SCHOOL_TYPE, @PathVariable("AUDIT_STATUS") String AUDIT_STATUS
            , @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        try {
            RestRecord restRecord = new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
            Page<List<Map<String, Object>>> page;
            StringBuilder sql = new StringBuilder( "SELECT\n" +
                    "\tses.ID,\n" +
                    "\tses.SCHOOL_NAME,\n" +
                    "\tses.SCHOOL_TYPE,\n" +
                    "\tses.REGION,\n" +
                    "\tses.SCHOOL_DISTRICT,\n" +
                    "\tscu.USER_NAME,\n" +
                    "\tscu.LOGIN_NAME,\n" +
                    "\tsua.AUDIT_STATUS,\n" +
                    "\tsua.AUDIT_TIME \n" +
                    "FROM\n" +
                    "\tSTARCLOUDPORTAL.SCE_ENTITY_SCHOOL ses\n" +
                    "\tLEFT JOIN STARCLOUDPORTAL.SCE_USER_AUDIT sua ON ses.ID = sua.ENTITY_ID\n" +
                    "\tLEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER scu ON sua.USER_ID = scu.USER_ID \n" +
                    "WHERE\n" +
                    "\tses.IS_DELETE = 1 " );
            if(!StringUtils.isEmpty(SCHOOL_NAME) && !"null".equals(SCHOOL_NAME)){
                sql.append("and ses.SCHOOL_NAME like '%"+SCHOOL_NAME+"%' ");
            }
            if(!StringUtils.isEmpty(SCHOOL_TYPE) && !"null".equals(SCHOOL_TYPE)){
                sql.append("and ses.SCHOOL_TYPE = "+SCHOOL_TYPE+" ");
            }
            if(StringUtils.isEmpty(AUDIT_STATUS) || "null".equals(AUDIT_STATUS) ){
                sql.append("AND ( sua.AUDIT_STATUS = 0  OR sua.AUDIT_STATUS = 2 ) ");
            }
            if(!StringUtils.isEmpty(AUDIT_STATUS) && "0".equals(AUDIT_STATUS) ){
                sql.append("AND  sua.AUDIT_STATUS = 0 ");
            }
            if(!StringUtils.isEmpty(AUDIT_STATUS) && "2".equals(AUDIT_STATUS) ){
                sql.append("AND  sua.AUDIT_STATUS = 2 ");
            }

            Session session = entityManager.unwrap( org.hibernate.Session.class );
            NativeQuery query = session.createNativeQuery( sql.toString() );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            int start = ( pageNum - 1 ) * pageSize;
            int total = query.getResultList().size();
            //判断分页
            if ( start < total && pageSize > 0 ) {
                query.setFirstResult( start );
                query.setMaxResults( pageSize );
            }
            Map< String, Object > temp = new HashMap<>( 16 );
            temp.put( "data", query.getResultList() );
            temp.put( "totalPage", ( total + pageSize - 1 ) / pageSize );
            temp.put( "totalCount", total );
            restRecord.setData( temp );
            return restRecord;
        } catch ( Exception e ) {
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
        }
    }

}

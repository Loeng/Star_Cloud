package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.dao.UserPasswordDao;
import cn.com.bonc.sce.entity.CompanyInfo;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.model.CompanyInfoModel;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.repository.CompanyInfoRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CompanyService;
import cn.com.bonc.sce.service.FileResourceService;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.utils.UUID;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeDefinition;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
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

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserPasswordDao passwordDao;

    @Autowired
    private CompanyService companyService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FileResourceService fileResourceService;

    @GetMapping
    @ResponseBody
    public RestRecord queryCompanyInfo(
            @RequestParam( value = "companyId", required = false ) Long companyId,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize ) {
        log.trace( "Query CompanyInfo List Conditions is {}" );
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        try {
            RestRecord restRecord = new RestRecord( 200 );
            Map< String, Object > result = new HashMap<>( 16 );
            Page< List< Map< String, Object > > > page;

            if ( companyId == null ) {
                page = companyInfoRepository.findCompanyInfoByCompanyName( companyName, pageable );
            } else {
                page = companyInfoRepository.findCompanyInfoByCompanyId( companyId, pageable );
            }
            result.put( "data", page.getContent() );
            result.put( "totalCount", page.getTotalElements() );
            result.put( "totalPage", page.getTotalPages() );
            restRecord.setData( result );
            return restRecord;
        } catch ( Exception e ) {
            log.error( "Query CompanyInfo fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }

    }

    /**
     * 查询单个厂商详细信息
     *
     * @param companyId 厂商Id
     * @return
     */
    @GetMapping( "/one/{companyId}" )
    @ResponseBody
    public RestRecord queryOneCompanyInfo(
            @PathVariable( value = "companyId" ) Long companyId ) {
        log.trace( "query one companyInfo ,companyId is :{}", companyId );
        try {
            RestRecord restRecord = new RestRecord( 200 );
            restRecord.setData( companyInfoRepository.findById( companyId ) );
            restRecord.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
            return restRecord;
        } catch ( Exception e ) {
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }

    @ApiOperation( value = "通过厂商ID获取厂商信息接口", notes = "通过厂商ID获取厂商信息", httpMethod = "GET" )
    @GetMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord getCompanyByCompanyId(
            @PathVariable( value = "companyId" ) @ApiParam( value = "厂商Id" ) Long companyId ) {
        CompanyInfo info =  companyService.getCompanyByCompanyId( companyId );
        if (info == null) {
            return new RestRecord(111, WebMessageConstants.SCE_PORTAL_MSG_111, companyId);
        } else {
            String picUrl = "";
            if(org.apache.commons.lang3.StringUtils.isNoneBlank(info.getInstitutionCode())) {
                Integer headPortrait = Integer.parseInt(info.getInstitutionCode());
                Map<String, Object> url = fileResourceService.getFileStorePath(headPortrait);
                picUrl = url == null ? "" : url.get("fileStorePath").toString();
            }
            info.setFileStorePath(picUrl);
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, info);
        }
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
        companyInfo.setIsDelete( 1L );
        log.trace( "add company :{}", companyInfo );
        try {
            //保存厂商实体
            companyInfo.setEstablishingTime( new Date() );
            companyInfo.setIsDelete( 1L );
            companyInfo = companyInfoRepository.saveAndFlush( companyInfo );
            //创建“管理员账号”
            String userId = UUID.getUUID();
            userInfoRepository.insertUser( userId, IDUtil.createID( "cj_" ), "", "", 4, "", 0, "", "",
                    "", new Date(), companyInfo.getCompanyId(), "厂商管理员", Secret.ES256GenerateSecret() );
            //创建密码
            UserPassword password = new UserPassword();
            password.setUserId( userId );
            password.setPassword( "star123!" );
            passwordDao.save( password );

            //关联资料表
            userInfoRepository.insertInfoCompany( userId, companyInfo.getCompanyId().toString(), "" );

            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            restRecord.setData( companyInfo );
            return restRecord;
        } catch ( Exception e ) {
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }

    @ApiOperation(value = "新增代理商信息接口", notes = "新增代理商信息", httpMethod = "POST" )
    @PostMapping("/addCompany/{roleId}")
    @ResponseBody
    public RestRecord addCompany(
            @RequestBody @ApiParam( name = "company", value = "代理商信息对象", required = true )
                    CompanyInfo company,@RequestParam("userId") String userId, @PathVariable("roleId") Integer roleId ) {
        return companyService.addCompany(company, userId, roleId);
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
            @PathVariable( "companyId" ) Long companyId,
            @RequestBody CompanyInfo companyInfo ) {
        log.trace( "Update companyInfo,companyId is : {} ,companyInfo is {}", companyId, companyInfo );
        try {
            companyInfo.setCompanyId( companyId );
            companyInfo.setIsDelete( 1L );
            companyInfoRepository.save( companyInfo );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( WebMessageConstants.SCE_PORTAL_MSG_501, e );
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
    }

    @ApiOperation( value = "通过厂商ID修改厂商信息接口", notes = "通过厂商ID修改厂商信息", httpMethod = "PUT" )
    @PutMapping( "/updateCompanyByCompanyId" )
    @ResponseBody
    public RestRecord updateCompanyByCompanyId(
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true )
                    CompanyInfo companyInfo ) {
        return companyService.updateCompanyByCompanyId(companyInfo);
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
    @Transactional
    public RestRecord deleteCompanyInfo(
            @PathVariable( "companyId" ) Long companyId ) {
        try {
            companyInfoRepository.deleteCompanyByCompanyId( companyId );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( "{}", e );
            return new RestRecord( 422, WebMessageConstants.SCE_PORTAL_MSG_422 );
        }
    }


    /**
     * 查询所有厂商用户
     *
     * @param loginName   账号
     * @param companyName 厂商名
     * @param enable      是否允许登录
     * @param pageNum     页数
     * @param pageSize    每页大小
     * @return
     */
    @GetMapping( "/user-info" )
    @ResponseBody
    public RestRecord getAllUserInfo(
            @RequestParam( value = "loginName", required = false, defaultValue = "" ) String loginName,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) String companyName,
            @RequestParam( value = "enable", required = false ) String enable,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        try {
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize, Sort.Direction.DESC, "CREATE_TIME" );
            Page page;
            if ( enable == null ) {
                page = companyInfoRepository.getAllCompanyUser( pageable, loginName, companyName );
            } else {
                page = companyInfoRepository.getAllCompanyUserAndEnable( pageable, loginName, companyName, enable );
            }
            Map< String, Object > temp = new HashMap<>( pageSize );
            if ( null != page ) {
                temp.put( "data", page.getContent() );
                temp.put( "totalPage", page.getTotalPages() );
                temp.put( "totalCount", page.getTotalElements() );
            }
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );
        } catch ( Exception e ) {
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }

    @ApiOperation(value = "变更或驳回提交厂商信息接口",notes = "变更或驳回提交厂商信息",httpMethod = "PUT")
    @PutMapping( "/updateCompany" )
    @ResponseBody
    public RestRecord updateCompany(@RequestBody @ApiParam( "厂商信息对象" ) CompanyInfo companyInfo) {
        return companyService.updateCompany(companyInfo);
    }

    @GetMapping( "/getCompanyList/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getCompanyList(@RequestParam(value = "companyName",required = false) @ApiParam( name = "companyName", value = "厂家名称") String companyName,@RequestParam(value = "property",required = false) @ApiParam( name = "property", value = "公司性质") String property, @RequestParam(value = "auditStatus",required = false) @ApiParam( name = "auditStatus", value = "审核状态") Integer auditStatus
            , @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        try {
            RestRecord restRecord = new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
            Page<List<Map<String, Object>>> page;
            StringBuilder sql = new StringBuilder( "SELECT\n" +
                    "\tto_char(smc.COMPANY_ID) AS ID,\n" +
                    "\tscu.USER_ID,\n" +
                    "\tsmc.COMPANY_NAME,\n" +
                    "\tsmc.PROPERTY,\n" +
                    "\tscu.USER_NAME,\n" +
                    "\tscu.LOGIN_NAME,\n" +
                    "\tsua.AUDIT_STATUS,\n" +
                    "\tsua.AUDIT_TIME \n" +
                    "FROM\n" +
                    "\tSTARCLOUDMARKET.SCE_MARKET_COMPANY smc\n" +
                    "\tLEFT JOIN STARCLOUDPORTAL.SCE_USER_AUDIT sua ON smc.COMPANY_ID = sua.ENTITY_ID\n" +
                    "\tLEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER scu ON sua.USER_ID = scu.USER_ID \n" +
                    "WHERE\n" +
                    "\tsmc.IS_DELETE = 1 " );
            if(!StringUtils.isEmpty(companyName) && !"null".equals(companyName)){
                sql.append("and smc.COMPANY_NAME like '%"+companyName+"%' ");
            }
            if(!StringUtils.isEmpty(property) && !"null".equals(property)){
                sql.append("and smc.PROPERTY = '"+property+"' ");
            }
            if(StringUtils.isEmpty(auditStatus) || "null".equals(auditStatus) ){
                sql.append("AND ( sua.AUDIT_STATUS = 0  OR sua.AUDIT_STATUS = 2 ) ");
            }
            if(!StringUtils.isEmpty(auditStatus) && "0".equals(auditStatus) ){
                sql.append("AND  sua.AUDIT_STATUS = 0 ");
            }
            if(!StringUtils.isEmpty(auditStatus) && "2".equals(auditStatus) ){
                sql.append("AND  sua.AUDIT_STATUS = 2 ");
            }
            sql.append("order by sua.AUDIT_TIME desc");

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

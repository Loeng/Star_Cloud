package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.dao.UserPasswordDao;
import cn.com.bonc.sce.entity.CompanyInfo;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.repository.CompanyInfoRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
            companyInfoRepository.saveAndFlush( companyInfo );
            //创建“管理员账号”
            String userId = UUID.getUUID();
            userInfoRepository.insertUser( userId, IDUtil.createID( "cj_" ), "", "", 4, "", 0, "", "",
                    "", new Date(), companyInfo.getCompanyId().toString(), "厂商管理员", Secret.generateSecret() );
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
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/user-info" )
    @ResponseBody
    public RestRecord getAllUserInfo(
            @RequestParam( value = "loginName", required = false, defaultValue = "" ) String loginName,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        try {
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize, Sort.Direction.DESC, "CREATE_TIME" );
            Page page = companyInfoRepository.getAllCompanyUser( pageable,loginName,companyName );
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
}

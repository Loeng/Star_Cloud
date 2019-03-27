package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 用户列表-查询api
 *
 * @author jc_D
 * @description
 * @date 2018/12/22
 **/
@Slf4j
@RestController
@RequestMapping( "/user-list" )
public class UserListController {


    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 根据角色id查询用户信息
     *
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping( "/role/{roleId}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getUserInfoByRole( @PathVariable( "roleId" ) Integer roleId,
                                         @PathVariable( "pageNum" ) Integer pageNum,
                                         @PathVariable( "pageSize" ) Integer pageSize,
                                         @RequestBody( required = false ) Map< String, Object > condition ) {

        // 此处在具体的角色ID确定下来之后更改分支走的方法
        Page page = null;
        pageNum = pageNum - 1;
        switch ( roleId ) {
            case 1:
                page = student( pageNum, pageSize, condition );  // 学生
                break;
            case 2:
                page = teacher( pageNum, pageSize, condition ); // 教师
                break;
            case 3:
                break;
            case 4:
                page = manufacturer( pageNum, pageSize, condition ); // 厂商
                break;
            case 5:
                page = family( pageNum, pageSize, condition ); // 家长
                break;
            case 6:
                // 代理商
                break;
            case 7:
                page = organization( pageNum, pageSize, condition );// 机构
                break;
            case 8:
                page = selfRegistration( pageNum, pageSize, condition );//自注冊
                break;
            default:
                break;
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, page );
    }

    // 查询 学校 -》 教师信息
    public Page< Map< String, Object > > teacher( int pageNum, int pageSize, Map< String, Object > condition ) {
        Page info;
        Pageable pageable = PageRequest.of( pageNum, pageSize, Sort.Direction.DESC, "CREATE_TIME" );
        String account = "";
        String name = "";
        String organization_name = "";
        String login = "";
        if ( !CollectionUtils.isEmpty( condition ) ) {
            log.info( "用户列表: 学校 -> 教师 信息模糊查询，查询条件为[{}]", condition );
            if ( null != condition.get( "account" ) && !"".equals( condition.get( "account" ) ) ) {
                account = condition.get( "account" ).toString();
            }
            if ( null != condition.get( "name" ) && !"".equals( condition.get( "name" ) ) ) {
                name = condition.get( "name" ).toString();
            }
            if ( null != condition.get( "organization_name" ) && !"".equals( condition.get( "organization_name" ) ) ) {
                organization_name = condition.get( "organization_name" ).toString();
            }
            if ( null != condition.get( "login" ) && !"".equals( condition.get( "login" ) ) ) {
                login = condition.get( "login" ).toString();
            }
        }
        info = userInfoRepository.findTeacherByCondition( name, account, organization_name, login, pageable );
        log.info( "一共查询到[{}]条符合条件的信息", info.getTotalElements() );
        return info;

    }

    // 查询 学校 -》 学生信息
    public Page< Map< String, Object > > student( int pageNum, int pageSize, Map< String, Object > condition ) {
        Page info;
        Pageable pageable = PageRequest.of( pageNum, pageSize, Sort.Direction.DESC, "CREATE_TIME" );
        String account = "";
        String name = "";
        String organization_name = "";
        String login = "";
        if ( !CollectionUtils.isEmpty( condition ) ) {
            log.info( "用户列表: 学校 -> 学生 信息模糊查询，查询条件为[{}]", condition );
            if ( null != condition.get( "account" ) && !"".equals( condition.get( "account" ) ) ) {
                account = condition.get( "account" ).toString();
            }
            if ( null != condition.get( "name" ) && !"".equals( condition.get( "name" ) ) ) {
                name = condition.get( "name" ).toString();
            }

            if ( null != condition.get( "organization_name" ) && !"".equals( condition.get( "organization_name" ) ) ) {
                organization_name = condition.get( "organization_name" ).toString();
            }
            if ( null != condition.get( "login" ) && !"".equals( condition.get( "login" ) ) ) {
                login = condition.get( "login" ).toString();
            }
        }
        info = userInfoRepository.findSchoolByCondition( name, account, organization_name, login, pageable );
        log.info( "一共查询到[{}]条符合条件的信息", info.getTotalElements() );
        return info;
    }

    @GetMapping( "/number" )
    @ResponseBody
    public RestRecord getUserInfoByRole() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set( Calendar.DAY_OF_WEEK, Calendar.MONDAY );
            cal.set( Calendar.HOUR_OF_DAY, 0 );
            cal.set( Calendar.MINUTE, 0 );
            cal.set( Calendar.SECOND, 0 );

            Date time = cal.getTime();
            //已开通用户数(IS_FIRST_LOGIN =1)
            Integer userCount = userInfoRepository.getUserCount();
            List< Map< String, Object > > roleCount = userInfoRepository.getRoleCount();
            Integer activeCount = userInfoRepository.getActiveCount( time );
            Float activeProportion = activeCount.floatValue() / userCount;
            DecimalFormat df = new DecimalFormat( "#.##%" );
            Map< String, Object > result = new HashMap<>();
            result.put( "userCount", userCount );
            result.put( "roleCount", roleCount );
            result.put( "activeCount", activeCount );
            result.put( "activeProportion", df.format( activeProportion ) );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, result );
        } catch ( Exception e ) {
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }

    // 查询 学校 -》 家长信息

    public Page< Map< String, Object > > family( int pageNum, int pageSize, Map< String, Object > condition ) {
        Page info;
        Pageable pageable = PageRequest.of( pageNum, pageSize, Sort.Direction.DESC, "CREATE_TIME" );
        String account = "";
        String name = "";
        String login = "";
        if ( !CollectionUtils.isEmpty( condition ) ) {
            log.info( "用户列表: 学校 -> 家长 信息模糊查询，查询条件为[{}]", condition );
            if ( null != condition.get( "account" ) && !"".equals( condition.get( "account" ) ) ) {
                account = condition.get( "account" ).toString();
            }
            if ( null != condition.get( "name" ) && !"".equals( condition.get( "name" ) ) ) {
                name = condition.get( "name" ).toString();
            }
            if ( null != condition.get( "login" ) && !"".equals( condition.get( "login" ) ) ) {
                login = condition.get( "login" ).toString();
            }
        }
        info = userInfoRepository.findFamilyByCondition( name, account, login, pageable );
        log.info( "一共查询到[{}]条符合条件的信息", info.getTotalElements() );
        return info;
    }

    /**
     * @Author : lyy
     * @Desc :查询自注册信息
     * @Date : 15:01 2018/12/26
     */
    public Page< Map< String, Object > > selfRegistration( int pageNum, int pageSize, Map< String, Object > condition ) {
        Page info = null;
        Pageable pageable = PageRequest.of( pageNum, pageSize, Sort.Direction.DESC, "CREATE_TIME" );
        String name = "";
        String account = "";
        String login = "";
        if ( !CollectionUtils.isEmpty( condition ) ) {
            if ( null != condition.get( "account" ) && !"".equals( condition.get( "account" ) ) ) {
                account = condition.get( "account" ).toString();
            }

            if ( null != condition.get( "name" ) && !"".equals( condition.get( "account" ) ) ) {
                name = condition.get( "name" ).toString();
            }
            if ( null != condition.get( "login" ) && !"".equals( condition.get( "login" ) ) ) {
                login = condition.get( "login" ).toString();
            }
        }
        info = userInfoRepository.findSelfRegALLByNameOrCount( name, account, login, pageable );
        log.info( "一共查询到[{}]条符合条件的信息", info.getTotalElements() );
        return info;
    }


    /**
     * @Author : lyy
     * @Desc : 查询机构信息
     * @Date : 15:04 2018/12/26
     */
    public Page< Map< String, Object > > organization( int pageNum, int pageSize, Map< String, Object > condition ) {
        Page info = null;
        Pageable pageable = PageRequest.of( pageNum, pageSize, Sort.Direction.DESC, "USER_ID" );
        String loginName = "";
        String organizationName = "";
        String status = "";
        if ( !CollectionUtils.isEmpty( condition ) ) {
            //模糊查询
            if ( null != condition.get( "account" ) && !"".equals( condition.get( "account" ) ) ) {
                loginName = condition.get( "account" ).toString();
            }
            if ( null != condition.get( "organizationName" ) && !"".equals( condition.get( "organizationName" ) ) ) {
                organizationName = condition.get( "organizationName" ).toString();
            }
            if ( null != condition.get( "login" ) && !"".equals( condition.get( "login" ) ) ) {
                status = condition.get( "login" ).toString();
            }
        }
        info = userInfoRepository.findByOrganizationLike( loginName, organizationName, status, pageable );
        log.info( "一共查询到[{}]条符合条件的信息", info.getTotalElements() );

        return info;
    }

    /**
     * @Author : lyy
     * @Desc：查询 厂商信息   :
     * @Date : 15:22 2018/12/26
     */
    public Page< Map< String, Object > > manufacturer( int pageNum, int pageSize, Map< String, Object > condition ) {
        Page info = null;
        Pageable pageable = PageRequest.of( pageNum, pageSize, Sort.Direction.DESC, "USER_ID" );
        String ManufacturerName = "";
        String loginName = "";
        String status = "";
        if ( !CollectionUtils.isEmpty( condition ) ) {
            if ( null != condition.get( "account" ) && !"".equals( condition.get( "account" ) ) ) {
                loginName = condition.get( "account" ).toString();
            }
            if ( null != condition.get( "manufacturerName" ) && !"".equals( condition.get( "manufacturerName" ) ) ) {
                ManufacturerName = condition.get( "manufacturerName" ).toString();
            }
            if ( null != condition.get( "login" ) && !"".equals( condition.get( "login" ) ) ) {
                status = condition.get( "login" ).toString();
            }
        }
        info = userInfoRepository.findByManufacturerLike( loginName, ManufacturerName, status, pageable );
        log.info( "一共查询到[{}]条符合条件的信息", info.getTotalElements() );
        return info;
    }

}

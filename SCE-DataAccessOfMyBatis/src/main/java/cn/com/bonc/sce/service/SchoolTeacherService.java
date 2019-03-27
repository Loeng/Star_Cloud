package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.mapper.SchoolTeacherListMapper;
import cn.com.bonc.sce.mapper.UserMapper;
import cn.com.bonc.sce.model.School;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学校工作台
 * 教师管理-相关接口
 */
@Slf4j
@Service
public class SchoolTeacherService {

    @Autowired
    private SchoolTeacherListMapper teacherListMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询教师列表
     *
     * @param condition
     * @param userId
     * @return
     */
    public RestRecord getTeacherList( Map< String, Object > condition,
                                      String userId,
                                      Integer pageNum,
                                      Integer pageSize
    ) {
        Long ORGANIZATION_ID = teacherListMapper.selectOrganizationIdByUserId( userId );
        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        } else {
            condition.put( "ORGANIZATION_ID", ORGANIZATION_ID );

            PageHelper.startPage( pageNum, pageSize );
            List< Map > data = teacherListMapper.getTeacherListByOrganizationId( condition );
            PageInfo pageInfo = new PageInfo( data );

            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, pageInfo );
        }

    }

    /**
     * 删除教师
     *
     * @param userId
     * @return
     */
    public RestRecord deleteTeacher( String userId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, teacherListMapper.deleteTeacher( userId ) );
    }

    /**
     * 学校用户
     * 新增教师
     *
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    public RestRecord addTeacher( Map< String, Object > teacherInfo, String userId ) {
        Object login_name = teacherInfo.get( "LOGIN_NAME" );
        if ( login_name == null ) {
            return new RestRecord( 155, WebMessageConstants.SCE_WEB_MSG_155 );
        } else if ( userMapper.selectUserIdByLoginName( login_name.toString() ) != null ) {
            return new RestRecord( 154, WebMessageConstants.SCE_WEB_MSG_154 );
        }

        Long ORGANIZATION_ID = teacherListMapper.selectOrganizationIdByUserId( userId );

        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        }
        //基础信息
        UserBean user = new UserBean();
        //给教师生成userId
        long newUserId = idWorker.nextId();
        String secret = Secret.generateSecret();
        user.setSecret( secret );
        user.setLoginName( login_name.toString() );
        user.setUserType( 2 );
        user.setIsDelete( 1 );
        user.setUserId( newUserId );
        user.setOrganizationId( ORGANIZATION_ID );
        user.setUserName( teacherInfo.get( "USER_NAME" ) == null ? null : teacherInfo.get( "USER_NAME" ).toString() );
        user.setGender( teacherInfo.get( "GENDER" ) == null ? null : teacherInfo.get( "GENDER" ).toString() );
        user.setCertificateNumber( teacherInfo.get( "CERTIFICATE_NUMBER" ) == null ? null : teacherInfo.get( "CERTIFICATE_NUMBER" ).toString() );
        user.setPhoneNumber( teacherInfo.get( "PHONE_NUMBER" ) == null ? null : teacherInfo.get( "PHONE_NUMBER" ).toString() );
        //账号密码相关信息
        AccountBean account = new AccountBean( idWorker.nextId(), teacherInfo.get( "PASSWORD" ).toString(), 1, newUserId );
        //教师信息
        if ( teacherInfo.get( "TEACH_TIME" ) != null ) {
            Long teach_time = ( Long ) teacherInfo.get( "TEACH_TIME" );
            Date date = new Timestamp( teach_time );
            teacherInfo.put( "TEACH_TIME", date );
        }
        teacherInfo.put( "USER_ID", newUserId );
        //插入数据
        int a = userMapper.saveUserSelective( user );
        int b = userMapper.saveAccount( account );
        int c = teacherListMapper.saveTeacher( teacherInfo );

        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );

    }

    /**
     * 编辑教师信息
     *
     * @param teacherInfo
     * @param userId      当前登陆用户的userId
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    public RestRecord editTeacher( Map< String, Object > teacherInfo, String userId ) {
        //1.基础信息
        String teacherId = ( String ) teacherInfo.get( "USER_ID" );

        UserBean user = new UserBean();
        user.setUserName( teacherInfo.get( "USER_NAME" ) == null ? null : teacherInfo.get( "USER_NAME" ).toString() );
        user.setGender( teacherInfo.get( "GENDER" ) == null ? null : teacherInfo.get( "GENDER" ).toString() );
        user.setCertificateNumber( teacherInfo.get( "CERTIFICATE_NUMBER" ) == null ? null : teacherInfo.get( "CERTIFICATE_NUMBER" ).toString() );
        user.setPhoneNumber( teacherInfo.get( "PHONE_NUMBER" ) == null ? null : teacherInfo.get( "PHONE_NUMBER" ).toString() );
        user.setStringUserId( teacherId );
        user.setIsFirstLogin( null );

        //教师信息
        if ( teacherInfo.get( "TEACH_TIME" ) != null ) {
            Long teach_time = ( Long ) teacherInfo.get( "TEACH_TIME" );
            Date date = new Timestamp( teach_time );
            teacherInfo.put( "TEACH_TIME", date );
        }
        //修改数据
        int a = userMapper.updateUserByUserIdSelective( user );
        //修改账号密码相关信息
        String password = ( String ) teacherInfo.get( "PASSWORD" );
        if ( !StringUtils.isEmpty( password ) ) {
            int b = userMapper.resetPwd( teacherId, password );
        }

        int c = teacherListMapper.editTeacher( teacherInfo );

        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );

    }

}

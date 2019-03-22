package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.StudentParentRel;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.mapper.SchoolStudentListMapper;
import cn.com.bonc.sce.mapper.SchoolTeacherListMapper;
import cn.com.bonc.sce.mapper.StudentParentRelMapper;
import cn.com.bonc.sce.mapper.UserMapper;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IDUtil;
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
 * 学生管理-相关接口
 */
@Slf4j
@Service
public class SchoolStudentService {

    @Autowired
    private SchoolStudentListMapper schoolStudentListMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentParentRelMapper studentParentRelMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询学生列表
     *
     * @param condition
     * @param userId
     * @return
     */
    public RestRecord getStudentList( Map< String, Object > condition,
                                      String userId,
                                      Integer pageNum,
                                      Integer pageSize
    ) {
        Long ORGANIZATION_ID = schoolStudentListMapper.selectOrganizationIdByUserId( userId );
        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        } else {
            condition.put( "ORGANIZATION_ID", ORGANIZATION_ID );

            PageHelper.startPage( pageNum, pageSize );
            List< Map > data = schoolStudentListMapper.getStudentListByOrganizationId( condition );
            PageInfo pageInfo = new PageInfo( data );

            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, pageInfo );
        }

    }

    /**
     * 删除学生
     *
     * @param userId
     * @return
     */
    public RestRecord deleteStudent( String userId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, schoolStudentListMapper.deleteStudent( userId ) );
    }

    /**
     * 学校用户
     * 新增学生
     *
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    public RestRecord addStudent( Map< String, Object > studentInfo, String userId ) {
        Object login_name = studentInfo.get( "LOGIN_NAME" );
        if ( login_name == null ) {
            return new RestRecord( 155, WebMessageConstants.SCE_WEB_MSG_155 );
        } else if ( userMapper.selectUserIdByLoginName( login_name.toString() ) != null ) {
            return new RestRecord( 154, WebMessageConstants.SCE_WEB_MSG_154 );
        }

        Long ORGANIZATION_ID = schoolStudentListMapper.selectOrganizationIdByUserId( userId );

        if ( ORGANIZATION_ID == null ) {
            return new RestRecord( 440, WebMessageConstants.SCE_PORTAL_MSG_440 );
        }
        //基础信息
        UserBean user = new UserBean();
        //给学生生成userId
        long studentUserId = idWorker.nextId();
        String secret = Secret.generateSecret();
        user.setSecret( secret );
        user.setLoginName( login_name.toString() );
        user.setUserType( 1 );
        user.setIsDelete( 1 );
        user.setUserId( studentUserId );
        user.setOrganizationId( ORGANIZATION_ID );
        user.setUserName( studentInfo.get( "USER_NAME" ) == null ? null : studentInfo.get( "USER_NAME" ).toString() );
        user.setGender( studentInfo.get( "GENDER" ) == null ? null : studentInfo.get( "GENDER" ).toString() );
        user.setCertificateNumber( studentInfo.get( "CERTIFICATE_NUMBER" ) == null ? null : studentInfo.get( "CERTIFICATE_NUMBER" ).toString() );
        user.setPhoneNumber( studentInfo.get( "PHONE_NUMBER" ) == null ? null : studentInfo.get( "PHONE_NUMBER" ).toString() );
        //账号密码相关信息
        AccountBean account = new AccountBean( idWorker.nextId(), studentInfo.get( "PASSWORD" ).toString(), 1, studentUserId );
        //学生信息
        studentInfo.put( "USER_ID", studentUserId );
        //插入数据
        int a = userMapper.saveUserSelective( user );
        int b = userMapper.saveAccount( account );
        int c = schoolStudentListMapper.saveStudent( studentInfo );
        //创建一个家长账号与之关联
        UserBean parentUser = new UserBean();
        //给家长生成userId
        long parentUserId = idWorker.nextId();
        parentUser.setSecret( Secret.generateSecret() );
        parentUser.setLoginName( IDUtil.createID( "jz_" ) );
        parentUser.setUserType( 5 );
        parentUser.setIsDelete( 1 );
        parentUser.setUserId( parentUserId );
        parentUser.setOrganizationId( ORGANIZATION_ID );
        parentUser.setUserName( studentInfo.get( "USER_NAME" ) == null ? null : studentInfo.get( "USER_NAME" ).toString() );
        AccountBean parentAccount = new AccountBean( idWorker.nextId(), "star123!", 1, parentUserId );
        int d = userMapper.saveUserSelective( parentUser );
        //给家长账号创建密码
        int e = userMapper.saveAccount( parentAccount );
        //创建关联关系
        StudentParentRel studentParentRel = new StudentParentRel( idWorker.nextId(), String.valueOf( parentUserId ), String.valueOf( studentUserId ), 1 );
        int f = studentParentRelMapper.save( studentParentRel );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, parentUserId );

    }

    /**
     * 编辑学生信息
     *
     * @param studentInfo
     * @param userId      当前登陆用的 userId
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    public RestRecord editStudent( Map< String, Object > studentInfo, String userId ) {
        String teacherId = ( String ) studentInfo.get( "USER_ID" );
        //1.基础信息
        UserBean user = new UserBean();

        user.setUserName( studentInfo.get( "USER_NAME" ) == null ? null : studentInfo.get( "USER_NAME" ).toString() );
        user.setGender( studentInfo.get( "GENDER" ) == null ? null : studentInfo.get( "GENDER" ).toString() );
        user.setCertificateNumber( studentInfo.get( "CERTIFICATE_NUMBER" ) == null ? null : studentInfo.get( "CERTIFICATE_NUMBER" ).toString() );
        user.setPhoneNumber( studentInfo.get( "PHONE_NUMBER" ) == null ? null : studentInfo.get( "PHONE_NUMBER" ).toString() );
        user.setStringUserId( teacherId );
        user.setIsFirstLogin( null );
        //修改基础信息
        int a = userMapper.updateUserByUserIdSelective( user );
        //修改账号密码
        String password = ( String ) studentInfo.get( "PASSWORD" );
        if ( !StringUtils.isEmpty( password ) ) {
            int b = userMapper.resetPwd( teacherId, password );
        }//修改学生资料表
        int c = schoolStudentListMapper.editStudent( studentInfo );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
    }

}

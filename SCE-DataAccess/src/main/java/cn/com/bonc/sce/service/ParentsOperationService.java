package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.*;
import cn.com.bonc.sce.entity.ParentInfo;
import cn.com.bonc.sce.entity.RoleRel;
import cn.com.bonc.sce.entity.StudentParentRel;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 家长操作
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class ParentsOperationService {

    private static final String PASSWORD = "password";
    private static final String USER_ID = "userId";

    @Autowired
    private UserParentDao userParentDao;
    /*@Autowired
    private RoleRelDao roleRelDao;*/
    @Autowired
    private StudentParentRelDao studentParentRelDao;
    @Autowired
    private ParentInfoDao parentInfoDao;
    @Autowired
    private UserPasswordDao userPasswordDao;

    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @return 添加结果
     */
    public RestRecord insertParentsInfo( ParentsInfo parentsInfo ) {
        String account = parentsInfo.getStudentAccount();
        String phone = studentParentRelDao.selectMainParentPhone( account );
        if ( StringUtils.isEmpty( phone ) || !phone.equals( parentsInfo.getParentPhone() ) ) {
            return new RestRecord( 430, WebMessageConstants.SCE_PORTAL_MSG_430 );
        }
        List< Map< String, String > > list = userParentDao.selectUserInfo( account );
        if ( list.size() > 0 ) {
            Map< String, String > map = list.get( 0 );
            String studentId = map.get( USER_ID );
            //存储用户表
            User u = new User();
            u.setLoginName( parentsInfo.getAccount() );
            u.setPhoneNumber( parentsInfo.getParentPhone() );
            u.setUserName( parentsInfo.getParentName() );
            u.setCertificateNumber( parentsInfo.getParentNum() );
            u.setIsDelete( 1 );
            u.setSecret( Secret.generateSecret() );
            u.setLoginPermissionStatus( 1 );
            u.setUserType( 8 );
            User user = userParentDao.save( u );
            String parentId = user.getUserId();

            //存储密码表
            UserPassword up = new UserPassword();
            up.setUserId( parentId );
            up.setPassword( parentsInfo.getPassword() );
            up.setIsDelete( 1 );
            userPasswordDao.save( up );

            //存储角色表
            /*RoleRel rrParent = new RoleRel();
            rrParent.setRoleId( 8 );
            rrParent.setUserId( parentId );
            roleRelDao.save( rrParent );
            RoleRel rrStudent = new RoleRel();
            rrStudent.setRoleId( 1 );
            rrStudent.setUserId( studentId );
            roleRelDao.save( rrStudent );*/

            //存储学生家长对应表
            StudentParentRel spr = new StudentParentRel();
            spr.setParentUserId( parentId );
            spr.setStudentUserId( studentId );
            spr.setIsMain( 0 );
            studentParentRelDao.save( spr );

            //存储家长信息表
            ParentInfo pi = new ParentInfo();
            pi.setUserId( parentId );
            pi.setFamilyRole( parentsInfo.getRelationship() );
            pi.setIsDelete( 1 );
            parentInfoDao.save( pi );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        }
        return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
    }

    public RestRecord getExamine(Integer pageNum, Integer pageSize) {
        Page< Map<String,Object> > page;
        pageNum--;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "CREATE_TIME");
        page = userParentDao.getUnExamine(pageable);
        List< Map<String,Object> > list = page.getContent();
        Map< String, Object > info = new HashMap<>();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200,info );
    }

    public RestRecord examine( List<String> list ) {
        return new RestRecord( 200, userParentDao.updateParentStatus(list));
    }
}

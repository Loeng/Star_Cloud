package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.dao.SchoolDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.dao.UserPasswordDao;
import cn.com.bonc.sce.entity.Account;
import cn.com.bonc.sce.entity.School;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.repository.FileResourceRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
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
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class SchoolService {
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;


    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private UserPasswordDao userPasswordDao;

    @Autowired
    private MessageService messageService;

    public static final String SCHOOL_CODE = "3";


    /**
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    public RestRecord insertSchool(School school){
        User user = new User();
        String secret = Secret.generateSecret();
        String loginName = IDUtil.createID( "xx_" );
        user.setSecret( secret );
        user.setLoginName( loginName );
        user.setUserType( 3 );
        user.setIsDelete( 1 );
        userDao.save( user );

        Account account = new Account();
        account.setUserId( user.getUserId() );
        account.setPassword( "star123!" );
        account.setIsDelete( 1 );
        accountDao.save( account );

        school.setIsDelete( 1 );
        return new RestRecord(200, schoolDao.save( school ));
    }

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    public RestRecord getAll( Integer pageNum, Integer pageSize ) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize, Sort.Direction.DESC ,"CREATE_TIME" );
        Page< List<Map<String,Object> > > page;
        page = schoolDao.selectAllSchool( 1,pageable);
        Map< String, Object > info = new HashMap<>();
        List<List<Map<String,Object>>> list = page.getContent();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }

    public RestRecord saveSchool( Map map ) {

        School school = new School();
        school.setSchoolName( String.valueOf( map.get( "schoolName" ) ) );
        school.setAuthorityName(String.valueOf(map.get( "authorityName" )));
        school.setSchoolAddress( String.valueOf(map.get( "schoolAddress" ).toString()) );
        school.setTelephone(String.valueOf(map.get( "telephone" ).toString())  );
        school.setInstitutionId(Integer.valueOf( map.get("institutionId" ).toString()));
        school.setGrade( String.valueOf( map.get( "grade" ).toString() ) );
        school.setIsDelete(1);
        //插入学校体表
        School newSchool = schoolDao.save( school );
        //生成学校用户 和  插入密码表
        UserPassword userPassword = new UserPassword();
        String secret = Secret.generateSecret();
        String loginName = IDUtil.createID( "xx_" );
        String userId = UUID.getUUID();
        userPassword.setIsDelete( 1 );
        userPassword.setPassword( "star123!" );
        userPassword.setUserId( userId );

        int info = fileResourceRepository.savaAllUserInfo( userId, "", "",
                loginName, SCHOOL_CODE, "", "", "",
                "", "",String.valueOf(newSchool.getId()) , secret );
        userPasswordDao.save( userPassword );

        //向第一次创建的学校管理员发送一条消息
//        Message message = new Message();
//        message.setContent( "恭喜您，您申请的学校管理员账号成功！" );
//        message.setSourceId("0");
//        message.setTargetId(userId );
//        messageService.insertMessage(message);

        return new RestRecord( 200, info );

    }

}

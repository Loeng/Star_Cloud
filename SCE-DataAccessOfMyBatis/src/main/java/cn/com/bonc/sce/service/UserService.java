package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.mapper.AppMarketMapper;
import cn.com.bonc.sce.mapper.UserMapper;
import cn.com.bonc.sce.rest.RestRecord;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Charles on 2019/3/6.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    public int saveUser(UserBean userBean) {
        return userDao.saveUser(userBean);
    }

    public int saveAccount(AccountBean account) {
        return userDao.saveAccount(account);
    }

    public int delUser(String id) {
        return userDao.delUser(id);
    }

    public int resetPwd(String id,String pwd) {
        return userDao.resetPwd(id,pwd);
    }

    public int updateLoginPermission(String id, int newStatus) {
        return userDao.updateLoginPermission(id,newStatus);
    }

    public RestRecord findTeacherList( String UserId, String pageNum, String pageSize ) {
        BigDecimal OrganizationId = userMapper.selectOrganizationId( UserId );
        try {
            PageHelper.startPage( Integer.parseInt( pageNum ), Integer.parseInt( pageSize ) );
        } catch ( NumberFormatException e ) {
            log.warn( "不支持的分页参数" );
            return new RestRecord( 433, WebMessageConstants.SCE_PORTAL_MSG_433 );
        }
        RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        List list = userMapper.selectTeacherList( OrganizationId );
        PageInfo< List > pageInfo = new PageInfo<>( list );
        restRecord.setData( list );
        restRecord.setTotal( pageInfo.getTotal() );
        return restRecord;
    }

    public RestRecord findTeacher() {
        return null;
    }
}

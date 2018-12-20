package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账号安全信息相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@RestController
@RequestMapping( "/accountSecurity" )
public class AccountApiController {
    @Autowired
    private AccountDao accountSecurityDao;

    /**
     * 修改账号信息
     *
     * @param password 账号密码
     * @param phone 手机号
     * @return 修改结果
     */
    @PatchMapping( "/{phone}" )
    @ResponseBody
    public RestRecord updateAccount( @PathVariable( "phone" )String phone, @RequestParam( "password" ) String password) {
        try {
            return new RestRecord( 200, accountSecurityDao.updateAccount( phone, password ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }
}

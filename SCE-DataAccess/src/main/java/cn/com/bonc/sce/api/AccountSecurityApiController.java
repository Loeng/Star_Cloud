package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.AccountSecurityDao;
import cn.com.bonc.sce.model.AccountSecurity;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账号安全信息相关
 */
@RestController
@RequestMapping( "/accountSecurity" )
public class AccountSecurityApiController {
    @Autowired
    private AccountSecurityDao accountSecurityDao;

    /**
     * 修改账号信息
     *
     * @param accountSecurity 账号信息
     * @return 修改结果
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord updateAccount(AccountSecurity accountSecurity){
        try {
            return new RestRecord( 200, accountSecurityDao.updateAccount(accountSecurity) );
        } catch ( Exception e ) {
            return new RestRecord( 500, "", e );
        }
    }
}

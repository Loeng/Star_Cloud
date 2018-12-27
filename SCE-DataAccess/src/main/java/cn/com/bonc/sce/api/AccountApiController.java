package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.entity.Account;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AccountService;
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
@RequestMapping( "/account-security" )
public class AccountApiController {
    @Autowired
    private AccountService accountService;

    /**
     * 修改账号信息
     *
     * @param account account
     * @return 修改结果
     */
    @PutMapping( "" )
    @ResponseBody
    public RestRecord updateAccount( @RequestBody Account account) {
        try {
            return accountService.updateAccount( account.getPhone(), account.getPassword() );
        }catch ( Exception e ){
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }
}

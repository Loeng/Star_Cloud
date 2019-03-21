package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.MessageDao;
import cn.com.bonc.sce.rest.RestRecord;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * mybatis消息
 *
 * @author jc_D
 * @description
 * @date 2019/3/14
 **/
@Slf4j
@RestController
@RequestMapping( "/message" )
public class MessageController {
    @Autowired
    private MessageDao messageDao;

    /**
     * 通知记录
     *
     * @param pageNum
     * @param pageSize
     *
     * @return
     */
    @GetMapping( "/recode/{pageNum}/{pageSize}" )
    public RestRecord getMessageRecord(
            @PathVariable( value = "pageNum" ) Integer pageNum,
            @PathVariable( value = "pageSize" ) Integer pageSize
    ) {
        PageHelper.startPage( pageNum, pageSize );
        List< Map > data = messageDao.getMessageRecord();
        PageInfo pageInfo = new PageInfo( data );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    /**
     * 接收情况
     *
     * @param pageNum
     * @param pageSize
     *
     * @return
     */
    @GetMapping( "/receiving-situation/{pageNum}/{pageSize}" )
    public RestRecord getReceivingSituation(
            @PathVariable( value = "pageNum" ) Integer pageNum,
            @PathVariable( value = "pageSize" ) Integer pageSize
    ) {
        PageHelper.startPage( pageNum, pageSize );
        List< Map > data = messageDao.getReceivingSituation();
        PageInfo pageInfo = new PageInfo( data );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }
}

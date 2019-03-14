package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.model.Message;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 消息通知
 *
 * @author jcd
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@FeignClient( "sce-data-mybatis" )
public interface MessageDaoforMybatis {
    /**
     * 通知记录
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping( value = "/message/recode/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getMessageRecord(
            @PathVariable( value = "pageNum" ) Integer pageNum,
            @PathVariable( value = "pageSize" ) Integer pageSize
    );

    /**
     * 接收情况
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping( value = "/message/receiving-situation/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getReceivingSituation(
            @PathVariable( value = "pageNum" ) Integer pageNum,
            @PathVariable( value = "pageSize" ) Integer pageSize
    );
}


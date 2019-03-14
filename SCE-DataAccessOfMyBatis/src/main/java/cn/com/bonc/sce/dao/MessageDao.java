package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.MessageMapper;
import cn.com.bonc.sce.mapper.WorkbenchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MessageDao {

    @Autowired( required = false )
    private MessageMapper messageMapper;

    //通知记录
    public List< Map > getMessageRecord() {
        return messageMapper.getMessageRecord();
    }

    //接收情况
    public List< Map > getReceivingSituation() {
        return messageMapper.getReceivingSituation();
    }

}

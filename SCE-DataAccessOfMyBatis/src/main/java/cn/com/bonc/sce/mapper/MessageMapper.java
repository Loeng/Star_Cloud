package cn.com.bonc.sce.mapper;

import java.util.List;
import java.util.Map;

public interface MessageMapper {
    //通知记录
    List< Map > getMessageRecord();

    //接收情况
    List< Map > getReceivingSituation();

}

package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDao {

    public boolean insertMessage(Message message) {
        return true;
    }

    public Integer deleteAnnouncementById(Integer announcementId) {
        return null;
    }

    public Integer deleteMessageById(Integer messageId) {
        return null;
    }

    public Integer updateMessageReadStatusById(Integer messageId) {
        return null;
    }

    public String getNewestMessageTimeByUserId( String userId ){
        return null;
    }

    public List<Message> getNewestMessageFromCommonInformation(String userId,String newestTime){
        return null;
    }

    public Integer insertNewestMessageToCommonUserInfo(List<Message> list){
        return null;
    }

    public List<Message> getNewestMessage(String userId){
        return null;
    }
}

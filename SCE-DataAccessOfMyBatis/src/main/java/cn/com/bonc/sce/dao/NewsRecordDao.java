package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.NewsRecordBean;
import cn.com.bonc.sce.mapper.NewsRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author BTW
 */
@Repository
public class NewsRecordDao {

    @Autowired
    private NewsRecordMapper newsRecordMapper;

    public int insertNewsRecord( NewsRecordBean newsRecordBean){
        return newsRecordMapper.insertNewsRecord( newsRecordBean );
    }
}

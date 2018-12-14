package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.HistoryAdviseDao;
import cn.com.bonc.sce.model.HistoryAdvise;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HistoryAdviseService {
    private HistoryAdviseDao historyHistoryAdviseDao;

    @Autowired
    public HistoryAdviseService( HistoryAdviseDao historyHistoryAdviseDao ) {
        this.historyHistoryAdviseDao = historyHistoryAdviseDao;
    }

    /**
     * 添加historyHistoryAdvise
     *
     * @param historyHistoryAdvise 用户信息
     * @return 是否添加成功
     */
    public RestRecord insertHistoryAdvise(HistoryAdvise historyHistoryAdvise){
        return historyHistoryAdviseDao.insertHistoryAdvise(historyHistoryAdvise);
    }

    /**
     * 通过id删除historyHistoryAdvise
     *
     * @param historyHistoryAdviseId  id
     * @return 删除是否成功
     */
    public RestRecord deleteHistoryAdviseById(String historyHistoryAdviseId){
        return historyHistoryAdviseDao.deleteHistoryAdviseById(historyHistoryAdviseId);
    }

    /**
     * 更新historyHistoryAdvise
     *
     * @param historyHistoryAdvise historyHistoryAdvise信息
     * @return historyHistoryAdvise
     */
    public RestRecord updateHistoryAdviseInfo( HistoryAdvise historyHistoryAdvise ){
        return historyHistoryAdviseDao.updateHistoryAdviseInfo(historyHistoryAdvise);
    }

    /**
     * 获取historyHistoryAdvise数据
     *
     * @param historyHistoryAdviseId historyHistoryAdviseId
     * @return historyHistoryAdvise数据
     */
    public RestRecord getHistoryAdviseById(String historyHistoryAdviseId){
        return historyHistoryAdviseDao.getHistoryAdviseById(historyHistoryAdviseId);
    }

    /**
     * 获取所有historyHistoryAdvise数据
     *
     * @return historyHistoryAdvise数据list
     */
    public RestRecord getAllHistoryAdvisesInfo(){
        return historyHistoryAdviseDao.getAllHistoryAdvisesInfo();
    }
}

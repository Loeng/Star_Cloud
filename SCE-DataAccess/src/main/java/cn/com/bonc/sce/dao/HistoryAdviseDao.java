package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.HistoryAdvise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryAdviseDao {

    public boolean insertHistoryAdvise(HistoryAdvise historyHistoryAdvise) {
        return true;
    }

    public Integer deleteHistoryAdviseById(String historyHistoryAdviseId) {
        return null;
    }

    public Integer updateHistoryAdviseInfo(HistoryAdvise historyHistoryAdvise) {
        return null;
    }

    public Integer updateHistoryAdviseUrl(String historyHistoryAdviseId,String url) {
        return null;
    }

    public HistoryAdvise getHistoryAdviseById( String historyHistoryAdviseId) {
        return null;
    }

    public List<HistoryAdvise> getAllHistoryAdvisesInfo() {
        return null;
    }
}

package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Advise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdviseDao {

    public boolean insertAdvise(Advise advise) {
        return true;
    }

    public Integer deleteAdviseById(String adviseId) {
        return null;
    }

    public Integer updateAdviseInfo(Advise advise) {
        return null;
    }

    public Integer updateAdviseUrl(String adviseId,String url) {
        return null;
    }

    public Advise getAdviseById( String adviseId) {
        return null;
    }

    public List<Advise> getAllAdvisesInfo() {
        return null;
    }
}

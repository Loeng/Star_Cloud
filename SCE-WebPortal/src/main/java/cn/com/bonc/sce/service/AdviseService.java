package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AdviseDao;
import cn.com.bonc.sce.model.Advise;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdviseService {
    private AdviseDao adviseDao;

    @Autowired
    public AdviseService( AdviseDao adviseDao ) {
        this.adviseDao = adviseDao;
    }

    /**
     * 添加advise
     *
     * @param advise 用户信息
     * @return 是否添加成功
     */
    public RestRecord insertAdvise(Advise advise){
        return adviseDao.insertAdvise(advise);
    }

    /**
     * 通过id删除advise
     *
     * @param adviseId  id
     * @return 删除是否成功
     */
    public RestRecord deleteAdviseById(String adviseId){
        return adviseDao.deleteAdviseById(adviseId);
    }

    /**
     * 更新advise
     *
     * @param advise advise信息
     * @return advise
     */
    public RestRecord updateAdviseInfo( Advise advise ){
        return adviseDao.updateAdviseInfo(advise);
    }

    /**
     * 获取advise数据
     *
     * @param adviseId adviseId
     * @return advise数据
     */
    public RestRecord getAdviseById(String adviseId){
        return adviseDao.getAdviseById(adviseId);
    }

    /**
     * 获取所有advise数据
     *
     * @return advise数据list
     */
    public RestRecord getAllAdvisesInfo(){
        return adviseDao.getAllAdvisesInfo();
    }
}

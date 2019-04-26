package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.*;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.FileResourceDao;
import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.dao.NewsReportDao;
import cn.com.bonc.sce.dao.UserInfoMybatisDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author szy
 */
@Slf4j
@Service
@Transactional
public class NewsReportService {

    @Autowired
    private NewsReportDao newsDao;


    @Autowired
    private IdWorker idWorker;

    /**
     * 举报新闻
     * @param newsBean
     * @return
     */
    public int insertNewsInfo(NewsReportBean newsBean){
        Long contentId = idWorker.nextId();
        newsBean.setReportId( contentId );

        return newsDao.insertReportInfo( newsBean );
    }

    /**
     * 获取举报列表
     * @param dto
     * @return
     */
    public List<Map> selectNewsReportList(NewsReportDTO dto) {
        return newsDao.selectNewsReportList(dto);
    }


    /**
     * 根据ID获取举报详情
     * @param id
     * @return
     */
    public Map selectReportInfo(String id) {
        return newsDao.selectReportInfo(id);
    }


    /**
     * 审核
     * @param dto
     * @return
     */
    public int approveReport( NewsReportDTO dto ){
        return newsDao.approveReport(dto);
    }
}

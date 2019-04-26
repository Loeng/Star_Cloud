package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NewsRecordDao;
import cn.com.bonc.sce.dao.NewsReportdDao;
import cn.com.bonc.sce.model.NewsRecordModel;
import cn.com.bonc.sce.model.NewsReportModel;
import cn.com.bonc.sce.model.ReportApproveModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author szy
 */
@Slf4j
@Service
public class NewsReportService {

    @Autowired
    private NewsReportdDao newsReportdDao;

    public RestRecord insertReort( NewsReportModel newsReportModel){
        return newsReportdDao.insertReort( newsReportModel );
    }


    public RestRecord selectReportList( Integer pageSize, Integer pageNum, NewsReportModel newsModel) {

        return newsReportdDao.selectReportList(pageSize, pageNum,newsModel);
    }

    /**
     * 获取详情
     * @param newsModel
     * @return
     */
    public RestRecord selectReportInfo( NewsReportModel newsModel) {

        return newsReportdDao.selectReportInfo(newsModel);
    }


    public RestRecord reportApprove(ReportApproveModel newsModel){
        return newsReportdDao.reportApprove(newsModel);
    }
}

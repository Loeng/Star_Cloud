package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.NewsParamModel;
import cn.com.bonc.sce.model.NewsRecordModel;
import cn.com.bonc.sce.model.NewsReportModel;
import cn.com.bonc.sce.model.ReportApproveModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author szy
 */
@Repository
@FeignClient( "sce-data-mybatis" )
public interface NewsReportdDao {

    /**
     * 添加新闻举报信息
     *
     * @param newsReportModel
     * @return
     */
    @RequestMapping( value = "/news-report/new-report", method = RequestMethod.POST )
    RestRecord insertReort(NewsReportModel newsReportModel);


    /**
     * 获取新闻列表
     * @return
     */
    @RequestMapping( value = "/news-report/select-report-list", method = RequestMethod.POST )
    RestRecord selectReportList(@RequestParam( "pageSize" ) Integer pageSize,
                              @RequestParam( "pageNum" ) Integer pageNum,
                                NewsReportModel newsModel);

    /**
     * 获取举报详情
     * @return
     */
    @RequestMapping( value = "/news-report/report-info", method = RequestMethod.POST )
    RestRecord selectReportInfo(NewsReportModel newsModel);


    /**
     * 审核举报内容
     * @return
     */
    @RequestMapping( value = "/news-report/report-approve", method = RequestMethod.POST )
    RestRecord reportApprove(ReportApproveModel model);
}

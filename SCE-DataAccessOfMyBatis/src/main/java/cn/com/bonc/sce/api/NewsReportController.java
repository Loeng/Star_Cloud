package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.NewsRecordBean;
import cn.com.bonc.sce.bean.NewsReportBean;
import cn.com.bonc.sce.bean.NewsReportDTO;
import cn.com.bonc.sce.bean.ReportApproveBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.dao.NewsRecordDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsReportService;
import cn.com.bonc.sce.tool.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author szy
 */
@Slf4j
@RestController
@RequestMapping( "/news-report" )
public class NewsReportController {

    @Autowired
    private NewsReportService newsReportService;

    @Autowired
    private NewsDao newsInfoDao;


    @PostMapping( "/new-report" )
    @ResponseBody
    public RestRecord insertNewsReport( @RequestBody NewsReportBean newsReportBean ) {
        try {

            int count = newsReportService.insertNewsInfo(newsReportBean);
            if ( count == 1 ) {
                return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
            } else {
                return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
            }
        } catch ( Exception e ) {
            log.error( "insert newsReport fail {}", e );
            return new RestRecord( 423, MessageConstants.SCE_MSG_409, e );
        }
    }

    @PostMapping("/select-report-list")
    @ResponseBody
    public RestRecord selectNewsReportList( @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize,
                                   @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                   @RequestBody NewsReportDTO dto){
        PageHelper.startPage( pageNum, pageSize );
        List<Map> list = newsReportService.selectNewsReportList(dto);
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @PostMapping( "/report-info" )
    @ResponseBody
    public RestRecord selectReportInfo( @RequestBody NewsReportDTO dto ) {

        Map map = newsReportService.selectReportInfo(dto.getReportId());
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, map );
    }


    @PostMapping( "/report-approve" )
    @ResponseBody
    public RestRecord reportApprove( @RequestBody ReportApproveBean dto ) {

        if("1".equals(dto.getApproveStatus()) || "0".equals(dto.getApproveStatus())){

            // 正确数据

            NewsReportDTO d = new NewsReportDTO();
            d.setApproveUserId(dto.getApproveUserId());
            d.setApproveStatus(dto.getApproveStatus());

            List<String> ids = dto.getIdList();
            for(String reportId :ids){

                if("0".equals(dto.getApproveStatus())){ //审批通过 新闻变为待审核 并且下架
                    Map reportInfo = newsReportService.selectReportInfo(reportId);
                    String contentId = reportInfo.get("CONTENT_ID").toString();
                    newsInfoDao.auditNewsInfo( "0", dto.getApproveUserId(), null, Long.parseLong(contentId) );
                    newsInfoDao.updateNewsPublishStatus(2,dto.getApproveUserId(),Long.parseLong(contentId));
                }

                d.setReportId(reportId);
                d.setApproveRemark(dto.getApproveRemark());
                int count = newsReportService.approveReport(d);//审核举报信息

                if ( count != 1 ) {
                    return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
                }

            }

            return new RestRecord( 200, MessageConstants.SCE_MSG_0200);

        }else{
            // 异常数据
            return new RestRecord( 423, MessageConstants.SCE_MSG_407);
        }


    }

}

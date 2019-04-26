package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.NewsParamBean;
import cn.com.bonc.sce.bean.NewsRecordBean;
import cn.com.bonc.sce.bean.NewsReportBean;
import cn.com.bonc.sce.bean.NewsReportDTO;

import java.util.List;
import java.util.Map;

/**
 * @author szy
 */
public interface NewsReportMapper {

    /**
     * 插入举报数据
     *
     * @param newsReportBean
     * @return
     */
    int insertReportInfo(NewsReportBean newsReportBean);


    /**
     * 获取举报列表
     * @param dto
     * @return
     */
    List<Map> selectNewsReportList(NewsReportDTO dto);


    /**
     * 审批举报
     * @param dto
     * @return
     */
    int approveReport( NewsReportDTO dto );

}

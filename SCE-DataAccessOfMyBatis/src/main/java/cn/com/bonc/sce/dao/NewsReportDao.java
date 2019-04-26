package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.NewsParamBean;
import cn.com.bonc.sce.bean.NewsRecordBean;
import cn.com.bonc.sce.bean.NewsReportBean;
import cn.com.bonc.sce.bean.NewsReportDTO;
import cn.com.bonc.sce.mapper.NewsRecordMapper;
import cn.com.bonc.sce.mapper.NewsReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Repository
public class NewsReportDao {

    @Autowired
    private NewsReportMapper newsReportMapper;

    public int insertReportInfo( NewsReportBean newsReportBean){
        return newsReportMapper.insertReportInfo( newsReportBean );
    }

    /**
     * 获取举报列表
     * @param dto
     * @return
     */
    public List<Map> selectNewsReportList(NewsReportDTO dto) {
        return newsReportMapper.selectNewsReportList(dto);
    }

    /**
     * 根据ID获取举报详情
     * @param id
     * @return map
     */
    public Map selectReportInfo(String id) {

        NewsReportDTO dto = new NewsReportDTO();
        dto.setReportId(id);

        List<Map> list = newsReportMapper.selectNewsReportList(dto);
        Map map = new HashMap();

        if(list.size()>0){
            map = list.get(0);
        }

        return map;
    }


    public int approveReport( NewsReportDTO dto ){
        return newsReportMapper.approveReport(dto);
    }
}

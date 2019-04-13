package cn.com.bonc.sce.model;

import java.util.Date;

/**
 * Created by suzhenyu on 2019/4/11.
 */
public class NewsParamModel extends NewsModel {

    private Integer sortWay; // 排序方式：按照发布时间正序、倒序

    private String startDate;

    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSortWay() {
        return sortWay;
    }

    public void setSortWay(Integer sortWay) {
        this.sortWay = sortWay;
    }
}

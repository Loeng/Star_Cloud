package cn.com.bonc.sce.model;

/**
 * Created by suzhenyu on 2019/4/11.
 */
public class NewsParamModel extends NewsModel {

    private Integer sortWay; // 排序方式：按照发布时间正序、倒序

    public Integer getSortWay() {
        return sortWay;
    }

    public void setSortWay(Integer sortWay) {
        this.sortWay = sortWay;
    }
}

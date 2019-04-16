package cn.com.bonc.sce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suzhenyu on 2019/4/16.
 */
public class NewsOrderModel {

    List< NewsStatusModel > newsList = new ArrayList<NewsStatusModel>();

    public List<NewsStatusModel> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsStatusModel> newsList) {
        this.newsList = newsList;
    }
}

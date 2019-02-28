package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.NavigationBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Charles on 2019/2/27.
 */
public interface NavigationMapper {

    List<NavigationBean> getNavListByChannel(@Param( "channelId" ) Integer channelId);

    int addNav(@Param( "columnName" ) String columnName,@Param( "columnUrl" ) String columnUrl,
               @Param("channelId") Integer channelId);

    int editNav(@Param( "columnName" ) String columnName,@Param( "columnUrl" ) String columnUrl,
               @Param("columnId") Integer columnId);
}

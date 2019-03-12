package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.NavigationBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.model.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Charles on 2019/2/27.
 */
public interface NavigationMapper {

    List<NavigationBean> getChannel(@Param( "channelType" ) Integer channelType);

    int addNav(@Param( "columnName" ) String columnName,@Param( "columnUrl" ) String columnUrl,
               @Param("channelId") Integer channelId);

    int editNav(@Param( "columnName" ) String columnName,@Param( "columnUrl" ) String columnUrl,
               @Param("columnId") Integer columnId);

    List<SchoolBean> getSchools(@Param("keywords") String keywords);

    List<Banner> getBanners(@Param( "schoolId" ) Integer schoolId);

    Integer editDefaultBanner( @Param( "schoolId" ) Integer schoolId,
                                   @Param( "newBanner" ) Integer newBanner );

    Integer delBanner( @Param( "bannerId" ) Integer bannerId );

}

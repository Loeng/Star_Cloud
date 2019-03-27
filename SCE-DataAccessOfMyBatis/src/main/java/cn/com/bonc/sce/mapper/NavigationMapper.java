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

    int addChannel(@Param( "channelName" ) String channelName,@Param( "channelUrl" ) String channelUrl,
                   @Param("channelId") long channelId,@Param("channelType") int channelType,@Param("isDeleted") int isDeleted);

    int editChannel(@Param( "channelName" ) String channelName,@Param( "channelUrl" ) String channelUrl,
                    @Param("channelId") String channelId);

    List<SchoolBean> getSchools(@Param("keywords") String keywords);

    List<Banner> getBanners(@Param( "schoolId" ) long schoolId);

    Integer editDefaultBanner( @Param( "schoolId" ) long schoolId,
                               @Param( "newBanner" ) Integer newBanner );

    Integer delBanner( @Param( "bannerId" ) long bannerId );

    Integer delChannel(@Param( "channelId" )String channelId);
}

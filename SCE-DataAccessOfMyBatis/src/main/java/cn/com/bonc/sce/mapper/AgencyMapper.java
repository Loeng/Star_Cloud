package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Charles on 2019/3/4.
 */
public interface AgencyMapper {
    int editActivity(@Param( "id" ) Integer id,@Param( "isActivate" ) Integer isActivate);

    int editInfo(@Param("id") Integer id,@Param("agentName") String agentName,
                 @Param("province") String province,@Param("city")String city, @Param("area")String area);
}

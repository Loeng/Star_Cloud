package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.AppTypeBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BTW
 */
public interface AppTypeMapper {

    /**
     * 查询所用应用类型列表
     *
     * @return
     */
    List< AppTypeBean > selectAppTypeList();

    /**
     * 添加新的应用分类
     *
     * @param appTypeBean
     * @return
     */
    int insertAppTypeInfo( AppTypeBean appTypeBean );

    /**
     * 更新应用分类名称
     *
     * @param appTypeId
     * @param appTypeName
     * @return
     */
    int updateAppTypeInfo( @Param( "appTypeId" ) Integer appTypeId,
                           @Param( "appTypeName" ) String appTypeName );

    /**
     * 删除应用分类信息
     *
     * @param appTypeId
     * @return
     */
    int deleteAppTypeInfo( @Param( "appTypeId" ) Integer appTypeId );
}

package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.AppTypeBean;
import cn.com.bonc.sce.mapper.AppTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author BTW
 */
@Repository
public class AppTypeDao {

    @Autowired( required = false )
    private AppTypeMapper appTypeMapper;

    public List< AppTypeBean > selectAppTypeList() {
        return appTypeMapper.selectAppTypeList();
    }

    public int insertAppTypeInfo( AppTypeBean appTypeBean ) {
        return appTypeMapper.insertAppTypeInfo( appTypeBean );
    }

    public int updateAppTypeInfo( Integer appTypeId, String appTypeName ) {
        return appTypeMapper.updateAppTypeInfo( appTypeId, appTypeName );
    }

    public int deleteAppTypeInfo( Integer appTypeId ) {
        return appTypeMapper.deleteAppTypeInfo( appTypeId );
    }
}

package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.FeignAppTypeDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 14:43
 */
@Slf4j
@Service
public class AppTypeService {

    private FeignAppTypeDao feignAppTypeDao;

    @Autowired
    public AppTypeService( FeignAppTypeDao feignAppTypeDao ) {
        this.feignAppTypeDao = feignAppTypeDao;
    }

    public RestRecord getAllAppTypeList() {
        return feignAppTypeDao.getAllAppTypeList();
    }

    public RestRecord addAppTypeInfo( String appTypeName ) {
        return feignAppTypeDao.addAppTypeInfo( appTypeName );
    }

    public RestRecord updateAppTypeInfo( String appTypeId, String appTypeName ) {
        Integer id = Integer.valueOf( appTypeId );
        return feignAppTypeDao.updateAppTypeInfo( id, appTypeName );
    }

    public RestRecord deleteAppTypeInfo( String appTypeId ) {
        Integer id = Integer.valueOf( appTypeId );
        return feignAppTypeDao.deleteAppTypeInfo( id );
    }

}

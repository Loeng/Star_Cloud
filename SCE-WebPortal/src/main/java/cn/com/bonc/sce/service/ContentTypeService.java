package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.ContentTypeDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BTW
 */
@Slf4j
@Service
public class ContentTypeService {

    @Autowired
    private ContentTypeDao contentTypeDao;

    public RestRecord getAllContentTypeList() {
        return contentTypeDao.getAllContentTypeList();
    }

    public RestRecord addContentTypeInfo( String contentType, Integer isShown, Integer showOrder ) {
        return contentTypeDao.addContentTypeInfo( contentType, isShown, showOrder );
    }

    public RestRecord updateContentTypeInfo( Integer id, String contentType, Integer isShown, Integer showOrder ) {
        return contentTypeDao.updateContentTypeInfo( id, contentType, isShown, showOrder );
    }

    public RestRecord deleteContentTypeInfo( Integer id ) {
        return contentTypeDao.deleteContentTypeInfo( id );
    }
}

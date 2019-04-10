package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.ContentTypeBean;
import cn.com.bonc.sce.mapper.ContentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author BTW
 */
@Repository
public class ContentTypeDao {

    @Autowired
    private ContentTypeMapper contentTypeMapper;

    public List< ContentTypeBean > selectContentTypeList() {
        return contentTypeMapper.selectContentTypeList();
    }

    public int insertContentTypeInfo( ContentTypeBean contentTypeBean ) {
        return contentTypeMapper.insertContentTypeInfo( contentTypeBean );
    }

    public int updateContentTypeInfo( ContentTypeBean contentTypeBean ) {
        return contentTypeMapper.updateContentTypeInfo( contentTypeBean );
    }

    public int deleteContentTypeInfo( Integer id ) {
        return contentTypeMapper.deleteContentTypeInfo( id );
    }
}

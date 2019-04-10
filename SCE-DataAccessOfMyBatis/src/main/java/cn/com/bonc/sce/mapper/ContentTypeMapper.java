package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.ContentTypeBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BTW
 */
public interface ContentTypeMapper {

    /**
     * 查询新闻栏目类型列表
     *
     * @return
     */
    List< ContentTypeBean > selectContentTypeList();

    /**
     * 添加新的新闻栏目
     *
     * @param contentTypeBean
     * @return
     */
    int insertContentTypeInfo( ContentTypeBean contentTypeBean );

    /**
     * 更新新闻栏目信息·
     *
     * @param contentTypeBean
     * @return
     */
    int updateContentTypeInfo( ContentTypeBean contentTypeBean );

    /**
     * 删除新闻栏目信息
     *
     * @param id
     * @return
     */
    int deleteContentTypeInfo( @Param( "id" ) Integer id );
}

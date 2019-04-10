package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.NewsBean;
import cn.com.bonc.sce.dao.FileResourceDao;
import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.dao.UserInfoMybatisDao;
import cn.com.bonc.sce.tool.IdWorker;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BTW
 */
@Slf4j
@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;
    @Autowired
    private UserInfoMybatisDao userInfoMybatisDao;
    @Autowired
    private FileResourceDao fileResourceDao;

    @Autowired
    private IdWorker idWorker;

    public int insertNewsInfo(NewsBean newsBean){
        Long contentId = idWorker.nextId();
        newsBean.setContentId( contentId );
        if( newsBean.getPicId() != null ) {
            String picUrl = fileResourceDao.getFileStorePath( newsBean.getPicId() );
            newsBean.setPicUrl( picUrl );
        }
        if( newsBean.getFileId() != null ) {
            String fileUrl = fileResourceDao.getFileStorePath( newsBean.getFileId() );
            newsBean.setFileUrl( fileUrl );
        }
        if( StrUtil.isBlank( newsBean.getAuthorName() )){
            String authorName = userInfoMybatisDao.getUserNameById( newsBean.getCreateUserId() );
            newsBean.setAuthorName( authorName );
        }
        newsBean.setIsDelete( 1 );
        newsBean.setContentStatus( "0" );
        return newsDao.insertNewsInfo( newsBean );
    }


}

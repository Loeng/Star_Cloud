package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.NewsBean;
import cn.com.bonc.sce.bean.NewsParamBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.FileResourceDao;
import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.dao.UserInfoMybatisDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Slf4j
@Service
@Transactional
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
        }else{
            newsBean.setPicId(0);
        }

        if( newsBean.getFileId() != null ) {
            String fileUrl = fileResourceDao.getFileStorePath( newsBean.getFileId() );
            newsBean.setFileUrl( fileUrl );
        }else{
            newsBean.setFileId(0);
        }
        if( StrUtil.isBlank( newsBean.getAuthorName() )){
            String authorName = userInfoMybatisDao.getUserNameById( newsBean.getCreateUserId() );
            newsBean.setAuthorName( authorName );
        }
        newsBean.setColumnId(100);//栏目ID 预留字段 暂不使用
        newsBean.setIsDelete( 1 );
        newsBean.setContentStatus( "0" );
        newsBean.setIsPublish( 0 );
        newsBean.setIsTop(0);
        newsBean.setTopOrder(0);
        return newsDao.insertNewsInfo( newsBean );
    }

    /**
     * 更新新闻
     * @param newsBean
     * @return
     */
    public int updateNewsInfo(NewsBean newsBean){

        if( newsBean.getPicId() != null ) {
            String picUrl = fileResourceDao.getFileStorePath( newsBean.getPicId() );
            newsBean.setPicUrl( picUrl );
        }
        return newsDao.updateNewsInfo( newsBean );
    }

    public int updateTopNewsOrder( List<Map> newsBeanList , String userId){
        try{
            for(Map newsBean: newsBeanList){
                if(newsBean.get("topOrder") != null && !"".equals(newsBean.get("topOrder"))){
                    newsDao.updateTopNewsOrder( Integer.valueOf( String.valueOf(newsBean.get("topOrder"))), Long.valueOf( String.valueOf(newsBean.get("contentId"))), userId );
                }
                if(newsBean.get("showOrder") != null && !"".equals(newsBean.get("showOrder"))){
                    newsDao.updateNormalNewsOrder( Integer.valueOf( String.valueOf(newsBean.get("showOrder"))), Long.valueOf( String.valueOf(newsBean.get("contentId"))), userId );
                }
            }
            return 1;
        } catch ( Exception e ){
            log.error( "update News Order fail {}", e );
            return 0;
        }
    }

    /**
     * 获取非头条列表
     * @param pageSize
     * @param pageNum
     * @param newsBean
     * @return
     */
    public RestRecord getNotTopList(Integer pageSize,
                                    Integer pageNum,
                                    NewsParamBean newsBean){
        PageHelper.startPage( pageNum, pageSize );
        List<NewsParamBean> list = newsDao.selectNotTopList(newsBean);
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }


}

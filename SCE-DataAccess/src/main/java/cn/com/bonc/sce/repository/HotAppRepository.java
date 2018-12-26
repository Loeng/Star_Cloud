package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 用户表
 *
 * @version 2.0
 * @update 添加列表查询
 * @updateFrom 2018/12/26 11:20
 * @updateAuthor wzm
 */
public interface HotAppRepository extends JpaRepository< AppInfoEntity, String > {
    //查询热门推荐相关信息
    Page< AppInfoEntity > findByIsHotRecommend( Long isHot, Pageable pageable );

    //查询所有热门应用的appId
    @Query( value = "SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_HOT_RECOMMEND =1", nativeQuery = true )
    List< String > getAllHotAppId();

    //更改热门应用状态
    @Transactional
    @Modifying
    @Query( value = "UPDATE AppInfoEntity SET IS_HOT_RECOMMEND = :state,UPDATE_TIME=sysdate,UPDATE_USER_ID = :uid WHERE APP_ID IN :appidList", nativeQuery = false )
    int updateHotApp( @Param( value = "appidList" ) List< String > ids,
                      @Param( value = "state" ) Long state,
                      @Param( value = "uid" ) String uid );

    @Query( value = "SELECT MAIN.APP_ID,MAIN.APP_NAME,MAIN.COMPANY_ID,MAIN.APP_ICON,MAIN.APP_NOTES,\n" +
            "CASE WHEN A.APP_ID IS NULL THEN '0' ELSE '1' END IS_OPEN,\n" +
            "CASE WHEN C.APP_ID IS NULL THEN '0' ELSE '1' END IS_DOWNLOAD,\n" +
            "CASE WHEN D.APP_ID IS NULL THEN '0' ELSE '1' END IS_COLLECT\n" +
            "FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO MAIN \n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN A ON A.APP_ID=MAIN.APP_ID AND A.IS_DELETE=1\n" +
            "LEFT JOIN (SELECT B.APP_ID,COUNT(*) FROM STARCLOUDMARKET.SCE_MARKER_APP_DOWNLOAD B GROUP BY B.APP_ID) C ON C.APP_ID=MAIN.APP_ID\n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION D ON D.APP_ID=MAIN.APP_ID AND D.IS_DELETE=1\n" +
            "WHERE MAIN.IS_HOT_RECOMMEND = 1", nativeQuery = true )
    List< Map<String,String> > selectHotAppList();
}
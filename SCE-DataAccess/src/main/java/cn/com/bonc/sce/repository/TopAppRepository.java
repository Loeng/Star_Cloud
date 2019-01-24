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
 * @author BTW
 */
public interface TopAppRepository extends JpaRepository< AppInfoEntity, String > {

    /**
     * 查询重点推荐应用相关信息
     *
     * @param isTop
     * @param pageable
     * @return
     */
    Page< AppInfoEntity > findByIsTopRecommend( Long isTop, Pageable pageable );

    /**
     * 查询所有重点推荐应用的appId
     *
     * @return
     */
    @Query( value = "SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_TOP_RECOMMEND =1 AND IS_DELETE = 1", nativeQuery = true )
    List< String > getAllTopAppId();

    /**
     * 更改重点推荐应用状态
     *
     * @param ids
     * @param state
     * @param uid
     * @return
     */
    @Transactional
    @Modifying
    @Query( value = "UPDATE AppInfoEntity SET IS_TOP_RECOMMEND = :state,UPDATE_TIME=sysdate,UPDATE_USER_ID = :uid WHERE APP_ID IN :appIdList", nativeQuery = false )
    Integer updateTopApp( @Param( value = "appIdList" ) List< String > ids,
                          @Param( value = "state" ) Long state,
                          @Param( value = "uid" ) String uid );

    /**
     * 修改重点推荐应用
     *
     * @param appId
     * @param state
     * @param userId
     * @return
     */
    @Transactional( rollbackOn = Exception.class )
    @Modifying( clearAutomatically = true )
    @Query( value = "UPDATE AppInfoEntity SET IS_TOP_RECOMMEND = :state,UPDATE_TIME=sysdate,UPDATE_USER_ID = :userId WHERE APP_ID = :appId ", nativeQuery = false )
    int updateTopAppById( @Param( value = "appId" ) String appId,
                          @Param( value = "state" ) Long state,
                          @Param( value = "userId" ) String userId );

    /**
     * 查询重点推荐详情列表
     *
     * @param userId
     * @param pageable
     * @return
     */
    @Query( value = "SELECT  AI.APP_ID,AI.APP_NAME,AI.COMPANY_ID,AI.APP_ICON,AI.APP_NOTES,AI.APP_SOURCE,AI.APP_LINK,AI.IS_TOP_RECOMMEND," +
            "                    NVL2(A.APP_ID ,'1','0') IS_OPEN," +
            "                    NVL2(C.APP_ID ,'1','0') IS_DOWNLOAD," +
            "                    NVL2(D.APP_ID ,'1','0') IS_COLLECT," +
            "                    NVL2( TR.USER_ID,'1', '0' ) IS_RECOMMEND" +
            "                    FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO AI " +
            " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
            "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
            "                       ) TEMPB ON AI.APP_ID = TEMPB.APP_ID \n" +
            "                    LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN A ON A.APP_ID=AI.APP_ID AND A.USER_ID = :userId AND A.IS_DELETE=1" +
            "                    LEFT JOIN (SELECT B.APP_ID,COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD B WHERE B.USER_ID = :userId GROUP BY B.APP_ID) C ON C.APP_ID=AI.APP_ID" +
            "                    LEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION D ON D.APP_ID=AI.APP_ID AND D.USER_ID = :userId AND D.IS_DELETE=1" +
            "                    LEFT JOIN STARCLOUDMARKET.SCE_TEACHER_RECOMMEND_APP TR ON AI.APP_ID=TR.APP_ID AND TR.IS_DELETE=1 AND TR.USER_ID=:userId" +
            "                    WHERE AI.IS_TOP_RECOMMEND = 1  AND AI.IS_DELETE = 1 " +
            "                    ORDER BY AI.CREATE_TIME DESC ",
            countQuery = "SELECT  COUNT(*)" +
                    "                    FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO AI " +
                    "         INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
                    "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
                    "                       ) TEMPB ON AI.APP_ID = TEMPB.APP_ID \n" +
                    "                    LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN A ON A.APP_ID=AI.APP_ID AND A.USER_ID = :userId AND A.IS_DELETE=1" +
                    "                    LEFT JOIN (SELECT B.APP_ID,COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD B WHERE B.USER_ID = :userId GROUP BY B.APP_ID) C ON C.APP_ID=AI.APP_ID" +
                    "                    LEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION D ON D.APP_ID=AI.APP_ID AND D.USER_ID = :userId AND D.IS_DELETE=1" +
                    "                    LEFT JOIN STARCLOUDMARKET.SCE_TEACHER_RECOMMEND_APP TR ON AI.APP_ID=TR.APP_ID AND TR.IS_DELETE=1 AND TR.USER_ID=:userId" +
                    "                    WHERE AI.IS_TOP_RECOMMEND = 1  AND AI.IS_DELETE = 1 " +
                    "                    ORDER BY AI.CREATE_TIME DESC ",
            nativeQuery = true )
    List< Map< String, Object > > selectTopAppList( @Param( value = "userId" ) String userId,
                                                    Pageable pageable );

}
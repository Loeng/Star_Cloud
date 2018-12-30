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
     * @param isTop
     * @param pageable
     * @return
     */
    Page< AppInfoEntity > findByIsTopRecommend( Long isTop, Pageable pageable );

    /**
     * 查询所有重点推荐应用的appId
     * @return
     */
    @Query( value = "SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_TOP_RECOMMEND =1", nativeQuery = true )
    List< String > getAllTopAppId();

    /**
     * 更改重点推荐应用状态
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
     * @param appId
     * @param state
     * @param userId
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    @Modifying( clearAutomatically = true )
    @Query( value = "UPDATE AppInfoEntity SET IS_TOP_RECOMMEND = :state,UPDATE_TIME=sysdate,UPDATE_USER_ID = :userId WHERE APP_ID = :appId ", nativeQuery = false )
    int updateTopAppById( @Param( value = "appId" ) String appId,
                          @Param( value = "state" ) Long state,
                          @Param( value = "userId") String userId );

    /**
     * 查询重点推荐详情列表
     * @param userId
     * @param pageable
     * @return
     */
    @Query( value = "SELECT MAIN.APP_ID,MAIN.APP_NAME,MAIN.COMPANY_ID,MAIN.APP_ICON,MAIN.APP_NOTES,MAIN.APP_SOURCE,MAIN.APP_LINK,\n" +
            "CASE WHEN A.APP_ID IS NULL THEN '0' ELSE '1' END IS_OPEN,\n" +
            "CASE WHEN C.APP_ID IS NULL THEN '0' ELSE '1' END IS_DOWNLOAD,\n" +
            "CASE WHEN D.APP_ID IS NULL THEN '0' ELSE '1' END IS_COLLECT\n" +
            "FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO MAIN \n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN A ON A.APP_ID=MAIN.APP_ID AND A.USER_ID = :userId AND A.IS_DELETE=1\n" +
            "LEFT JOIN (SELECT B.APP_ID,COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD B WHERE B.USER_ID = :userId GROUP BY B.APP_ID) C ON C.APP_ID=MAIN.APP_ID\n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION D ON D.APP_ID=MAIN.APP_ID AND D.USER_ID = :userId AND D.IS_DELETE=1\n" +
            "WHERE MAIN.IS_TOP_RECOMMEND = 1",
            countQuery = "SELECT COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_TOP_RECOMMEND = 1",
            nativeQuery = true )
    List< Map<String,String> > selectTopAppList( @Param( value = "userId" ) String userId,
                                                 Pageable pageable );

}
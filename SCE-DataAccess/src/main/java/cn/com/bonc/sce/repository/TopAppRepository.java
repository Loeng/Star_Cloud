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
}
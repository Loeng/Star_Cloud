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
}
package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface HotAppRepository extends JpaRepository< AppInfoEntity, Long > {


    //查询所有热门应用的appId
    @Query( value = "select APP_ID from SCE_MARKET_APP_INFO where IS_HOT_RECOMMEND =1", nativeQuery = true )
    List< String > getAllHotAppId();

    //更改热门应用状态
    @Transactional
    @Modifying
    @Query( value = "UPDATE AppInfoEntity SET IS_HOT_RECOMMEND = :state,UPDATE_TIME=sysdate,UPDATE_USER_ID = :uid WHERE APP_ID IN :appidList", nativeQuery = false )
    Integer updateHotApp( @Param( value = "appidList" ) List< String > ids,
                          @Param( value = "state" ) Long state,
                          @Param( value = "uid" ) Long uid );
}
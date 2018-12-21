package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.entity.AppTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppInfoRepository extends JpaRepository< AppInfoEntity, String > {

    //根据平台类型查询有哪些软件
    Page< AppInfoEntity > findByAppSource( String plantformType, Pageable pageable );

    //查应用详情
    AppInfoEntity findByAppId( String appId );

    //根据输入名模糊查询应用
    Page< AppInfoEntity > findByAppNameLike( String appName, Pageable pageable );
}
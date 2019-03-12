package cn.com.bonc.sce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.com.bonc.sce.entity.AppNameTypeEntity;

public interface AppFindRepository extends JpaRepository< AppNameTypeEntity, String >,JpaSpecificationExecutor<AppNameTypeEntity>{

}

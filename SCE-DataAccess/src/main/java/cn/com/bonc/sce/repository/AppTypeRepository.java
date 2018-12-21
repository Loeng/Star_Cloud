package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppTypeEntity;
import cn.com.bonc.sce.model.AppType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppTypeRepository extends JpaRepository< AppTypeEntity, String > {
    Page< AppTypeEntity > findByAppTypeId( String AppTypeId, Pageable pageable );
}
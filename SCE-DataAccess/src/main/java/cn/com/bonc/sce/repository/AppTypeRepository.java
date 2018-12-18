package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.model.app.AppTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppTypeRepository extends JpaRepository< AppTypeEntity,Long> {
        List<AppTypeEntity> findByAppTypeId( Long appTypeId );
}
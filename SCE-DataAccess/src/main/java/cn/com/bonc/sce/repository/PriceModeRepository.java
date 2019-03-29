package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.PriceModeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceModeRepository extends JpaRepository< PriceModeEntity, String > {
    @Override
    < S extends PriceModeEntity > S save( S s );

}

package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppTypeRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppTypeRelRepository extends JpaRepository< AppTypeRelEntity, String > {

    @Override
    < S extends AppTypeRelEntity > S saveAndFlush( S s );
}
package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppTypeRepository extends JpaRepository< AppTypeEntity, String > {

}
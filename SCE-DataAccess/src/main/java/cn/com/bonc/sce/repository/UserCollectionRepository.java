package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.AppCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCollectionRepository extends JpaRepository< AppCollectionEntity, String> {

    @Override
    < S extends AppCollectionEntity > S save( S s );
}

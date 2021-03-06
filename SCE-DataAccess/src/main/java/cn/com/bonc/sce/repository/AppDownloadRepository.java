package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.model.AppVersionQueryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
public interface AppDownloadRepository extends JpaRepository< AppInfoEntity, String > {

}

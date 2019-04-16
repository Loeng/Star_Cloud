package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.CompanyInfo;
import cn.com.bonc.sce.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/4/16.
 */
@Repository
public interface CompanyDao extends JpaRepository<CompanyInfo, Integer > {


}

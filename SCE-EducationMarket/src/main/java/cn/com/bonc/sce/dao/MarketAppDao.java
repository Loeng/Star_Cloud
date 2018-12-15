package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

@Repository
@FeignClient( "sce-data-access" )
public interface MarketAppDao {

}

package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.bonc.sce.rest.RestRecord;

@Repository
@FeignClient( "sce-data-access" )
public interface AppRankingDao {
		
	 @RequestMapping( value = "/rank-app/top-rank-app", method = RequestMethod.GET )
	 RestRecord getTopRankAppList( @RequestParam( value = "topSize", required = false, defaultValue = "10" ) Integer topSize);
}

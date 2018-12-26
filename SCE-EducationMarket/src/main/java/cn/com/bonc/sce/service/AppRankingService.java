package cn.com.bonc.sce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bonc.sce.dao.AppManageDao;
import cn.com.bonc.sce.dao.AppRankingDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppRankingService {
	
	private AppRankingDao appRankingDao;
	
	 @Autowired
	 public AppRankingService( AppRankingDao appRankingDao ) {
		 this.appRankingDao = appRankingDao;
	 }
	 
	  public RestRecord getTopRankAppList(Integer topSize ) {
	        return appRankingDao.getTopRankAppList(topSize);
	    }
}	

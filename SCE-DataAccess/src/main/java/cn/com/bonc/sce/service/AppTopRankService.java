package cn.com.bonc.sce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.bonc.sce.constants.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppTopRankView;
import cn.com.bonc.sce.repository.AppTopRankRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class AppTopRankService {
	
	 @Autowired
	 private AppTopRankRepository appTopRankRepository;
	 
	 public  RestRecord getAppTopRank( Integer topSize, String userId ) {
		
//	        Sort sort_p =Sort.by(Sort.Direction.DESC, "createTime");
//	        Pageable pageable = PageRequest.of(0, topSize, sort_p);
		 Pageable pageable = PageRequest.of( 0, topSize );
		 List< Map<String,String>> list = appTopRankRepository.selectTopAppList( userId, pageable );
		 //Page< AppInfoEntity > list = appInfoRepository.findAll(spec, pageable );
		 Map temp = new HashMap<>();
		 temp.put( "data", list );
		 return new RestRecord( 200, MessageConstants.SCE_MSG_0200, temp );
	 }

}

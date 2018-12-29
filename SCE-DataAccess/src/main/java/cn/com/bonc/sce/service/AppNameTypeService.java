package cn.com.bonc.sce.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppDownloadCountView;
import cn.com.bonc.sce.entity.AppNameTypeEntity;
import cn.com.bonc.sce.entity.AppTypeEntity;
import cn.com.bonc.sce.entity.CompanyInfo;
import cn.com.bonc.sce.repository.AppFindRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class AppNameTypeService {
	 @Autowired
	 private AppFindRepository appFindRepository;
	 
	 public RestRecord selectAppListByNameAndType( String appName,
             									   Integer appType,
             									   final String orderType,
             									   final String sort,
             									   String platformType,
             									   Integer pageNum,
             									   Integer pageSize) {
		 //TODO
		 /*if(StringUtils.isEmpty(sort)||sort.toUpperCase().equals("DESC")) {
			 	sort="DESC";
		 	}else {
		 		sort="ASC";
		 	}*/
		 
		 //Sort sort_p =Sort.by(Sort.Direction.fromString(sort), orderType);
		 Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

		 @SuppressWarnings("serial")
		 Specification<AppNameTypeEntity> spec = new Specification< AppNameTypeEntity >() {
			 @Override
			 public Predicate toPredicate(Root<AppNameTypeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				 	Predicate predicate = cb.conjunction();
				 	if (!StringUtils.isEmpty(appName)&&appName.trim().length()>0) {
				 		predicate.getExpressions().add(cb.like(root.get("appName").as(String.class), "%" + appName + "%"));
				 	}
				 	Join<AppTypeEntity, AppNameTypeEntity> join = root.join("appTypes", JoinType.LEFT);
				 	if (appType>0) {
				 		predicate.getExpressions().add(cb.equal(join.get("appTypeId").as(Integer.class), appType));
				 	}
				 	predicate.getExpressions().add(cb.equal(join.get("isDelete"), 1));
				 	Join<CompanyInfo, AppNameTypeEntity> joinc = root.join("companyInfo", JoinType.LEFT);
				 	predicate.getExpressions().add(cb.equal(joinc.get("isDelete"), 1));
				 	if (!StringUtils.isEmpty(platformType)&&platformType.trim().length()>0&&!"0".equals(platformType)) {
				 		predicate.getExpressions().add(cb.equal(root.get("appSource").as(String.class), platformType.trim()));
				 	}
				 	Join<AppDownloadCountView, AppNameTypeEntity> joinn = root.join("appDownloadCount", JoinType.LEFT);
				 	 if(orderType.equals("download")) {
						 if(sort.toUpperCase().equals("ASC")) {
							 query.orderBy(cb.asc(joinn.get("downloadCount").as(Long.class)));
						 }else {
							 query.orderBy(cb.desc(joinn.get("downloadCount").as(Long.class)));
						 }
					 }else if(orderType.equals("time")){
						 if(sort.toUpperCase().equals("ASC")) {
							 query.orderBy(cb.asc(root.get("updateTime").as(java.util.Date.class)));
						 }else {
							 query.orderBy(cb.desc(root.get("updateTime").as(java.util.Date.class)));
						 }
						 
					 }
				 	return predicate;
			 }
		 };
		 Page<AppNameTypeEntity> list = appFindRepository.findAll(spec,pageable);
		 Map temp = new HashMap<>();
		 temp.put( "data", list.getContent() );
		 temp.put( "totalPage", list.getTotalPages() );
		 temp.put( "totalCount", list.getTotalElements() );
		 return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );
	 }
}

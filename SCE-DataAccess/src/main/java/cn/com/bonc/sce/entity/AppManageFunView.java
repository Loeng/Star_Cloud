package cn.com.bonc.sce.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "APP_MANAGE_FUN_VIEW", schema = "STARCLOUDMARKET" )
public class AppManageFunView {
	@Id
	@Column( name = "APP_ID" )
	private String appId;
	
	@Column( name = "APP_NAME" )
	private String appName;

	@Column( name = "UPDATE_TIME" )
	private java.util.Date updateTime;
	@Column( name = "COMPANY_ID" )
	private Long companyId;
	@Column( name = "COMPANY_NAME" )
	private String companyName;
	/*@Column( name = "APP_TYPE_ID" )
	private Integer appTypeId;
	@Column( name = "APP_TYPE_NAME" )
	private String appTypeName;*/
	
	@Column( name = "APP_ICON" )
	private String appIcon;
	@Column( name = "APP_SOURCE" )
	private String appSource;
	@Column( name = "DOWNLOAD_COUNT" )
	private Long downloadCount;
}

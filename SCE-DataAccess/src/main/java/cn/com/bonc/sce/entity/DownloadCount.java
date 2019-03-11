package cn.com.bonc.sce.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: yhb
 * @Date: 2018/12/26 15:02
 * @Version 1.0
 */
@Entity
@Table( name = "SCE_MARKET_APP_DOWNLOAD", schema = "STARCLOUDMARKET", catalog = "" )
public class DownloadCount {
    private long id;
    private String appId;
    private Date downloadTime;
    private String userId;
    private String downloadUserIp;
    private Long isDelete = 1L;
    private String appVersion;

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "ID", nullable = false, precision = 0 )
    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "APP_ID", nullable = true, precision = 0 )
    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    @Basic
    @Column( name = "DOWNLOAD_TIME" )
    @CreationTimestamp
    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime( Date downloadTime ) {
        this.downloadTime = downloadTime;
    }

    @Basic
    @Column( name = "USER_ID" )
    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    @Basic
    @Column( name = "DOWNLOAD_USER_IP" )
    public String getDownloadUserIp() {
        return downloadUserIp;
    }

    public void setDownloadUserIp( String downloadUserIp ) {
        this.downloadUserIp = downloadUserIp;
    }

    @Basic
    @Column( name = "IS_DELETE" )
    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column( name = "APP_VERSION" )
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion( String appVersion ) {
        this.appVersion = appVersion;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        DownloadCount that = ( DownloadCount ) o;
        return id == that.id &&
                Objects.equals( appId, that.appId ) &&
                Objects.equals( downloadTime, that.downloadTime ) &&
                Objects.equals( userId, that.userId ) &&
                Objects.equals( downloadUserIp, that.downloadUserIp ) &&
                Objects.equals( isDelete, that.isDelete ) &&
                Objects.equals( appVersion, that.appVersion );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, appId, downloadTime, userId, downloadUserIp, isDelete, appVersion );
    }
}

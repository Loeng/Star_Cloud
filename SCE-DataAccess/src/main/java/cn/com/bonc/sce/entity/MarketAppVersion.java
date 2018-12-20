package cn.com.bonc.sce.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/16.
 */
@Entity
@Table( name = "SCE_MARKET_APP_VERSION", schema = "STARCLOUDMARKET", catalog = "" )
@IdClass( MarketAppVersionPK.class )
public class MarketAppVersion implements Serializable {
    private String appId;
    private String appVersion;
    private String appStatus;
    private String appDownloadAddress;
    private Timestamp createTime;
    private String versionInfo;
    private String versionSize;
    private String runningPlatform;
    private int isDelete;
    private String createUserId;
    private String updateUserId;
    private Timestamp updateTime;

    @Id
    @Column( name = "APP_ID" )
    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    @Id
    @Column( name = "APP_VERSION" )
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion( String appVersion ) {
        this.appVersion = appVersion;
    }

    @Basic
    @Column( name = "APP_STATUS" )
    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus( String appStatus ) {
        this.appStatus = appStatus;
    }

    @Basic
    @Column( name = "APP_DOWNLOAD_ADDRESS" )
    public String getAppDownloadAddress() {
        return appDownloadAddress;
    }

    public void setAppDownloadAddress( String appDownloadAddress ) {
        this.appDownloadAddress = appDownloadAddress;
    }

    @Basic
    @Column( name = "CREATE_TIME" )
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime( Timestamp createTime ) {
        this.createTime = createTime;
    }

    @Basic
    @Column( name = "VERSION_INFO" )
    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo( String versionInfo ) {
        this.versionInfo = versionInfo;
    }

    @Basic
    @Column( name = "VERSION_SIZE" )
    public String getVersionSize() {
        return versionSize;
    }

    public void setVersionSize( String versionSize ) {
        this.versionSize = versionSize;
    }

    @Basic
    @Column( name = "RUNNING_PLATFORM" )
    public String getRunningPlatform() {
        return runningPlatform;
    }

    public void setRunningPlatform( String runningPlatform ) {
        this.runningPlatform = runningPlatform;
    }

    @Basic
    @Column( name = "IS_DELETE" )
    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( int isDelete ) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column( name = "CREATE_USER_ID" )
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId( String createUserId ) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column( name = "UPDATE_USER_ID" )
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId( String updateUserId ) {
        this.updateUserId = updateUserId;
    }

    @Basic
    @Column( name = "UPDATE_TIME" )
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime( Timestamp updateTime ) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        MarketAppVersion that = ( MarketAppVersion ) o;
        return appId == that.appId &&
                Objects.equals( appVersion, that.appVersion ) &&
                Objects.equals( appStatus, that.appStatus ) &&
                Objects.equals( appDownloadAddress, that.appDownloadAddress ) &&
                Objects.equals( createTime, that.createTime ) &&
                Objects.equals( versionInfo, that.versionInfo ) &&
                Objects.equals( versionSize, that.versionSize ) &&
                Objects.equals( runningPlatform, that.runningPlatform ) &&
                Objects.equals( isDelete, that.isDelete ) &&
                Objects.equals( createUserId, that.createUserId ) &&
                Objects.equals( updateUserId, that.updateUserId ) &&
                Objects.equals( updateTime, that.updateTime );
    }

    @Override
    public int hashCode() {

        return Objects.hash( appId, appVersion, appStatus, appDownloadAddress, createTime, versionInfo, versionSize, runningPlatform, isDelete, createUserId, updateUserId, updateTime );
    }
}

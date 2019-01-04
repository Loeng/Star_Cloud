package cn.com.bonc.sce.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Entity
@Table( name = "SCE_MARKET_APP_VERSION", schema = "STARCLOUDMARKET" )
@IdClass( MarketAppVersionPK.class )
public class MarketAppVersion implements Serializable {
    private String appId;
    private String appVersion;
    private String appStatus;
    private String appDownloadAddress;
    private Date createTime;
    private String versionInfo;
    private String versionSize;
    private String runningPlatform;
    private Long isDelete;
    private String createUserId;
    private String updateUserId;
    private Date updateTime;
    private String newFeatures;
    private String packageName;
    private String authDetail;
    private String appPhonePic;
    private String appPcPic;
    private String lastVersion;

    @Basic
    @Column( name = "APP_PHONE_PIC", nullable = true, length = 50 )
    public String getAppPhonePic() {
        return appPhonePic;
    }

    public void setAppPhonePic( String appPhonePic ) {
        this.appPhonePic = appPhonePic;
    }

    @Basic
    @Column( name = "APP_PC_PIC", nullable = true )
    public String getAppPcPic() {
        return appPcPic;
    }

    public void setAppPcPic( String appPcPic ) {
        this.appPcPic = appPcPic;
    }

    @Id
    @Column( name = "APP_ID", nullable = false, length = 30 )
    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    @Id
    @Column( name = "APP_VERSION", nullable = false, length = 30 )
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion( String appVersion ) {
        this.appVersion = appVersion;
    }

    @Basic
    @Column( name = "APP_STATUS", nullable = true, length = 30 )
    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus( String appStatus ) {
        this.appStatus = appStatus;
    }

    @Basic
    @Column( name = "APP_DOWNLOAD_ADDRESS", nullable = true, length = 50 )
    public String getAppDownloadAddress() {
        return appDownloadAddress;
    }

    public void setAppDownloadAddress( String appDownloadAddress ) {
        this.appDownloadAddress = appDownloadAddress;
    }

    @Basic
    @Column( name = "CREATE_TIME", nullable = true )
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime( Date createTime ) {
        this.createTime = createTime;
    }

    @Basic
    @Column( name = "VERSION_INFO", nullable = true, length = 200 )
    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo( String versionInfo ) {
        this.versionInfo = versionInfo;
    }

    @Basic
    @Column( name = "VERSION_SIZE", nullable = true, length = 20 )
    public String getVersionSize() {
        return versionSize;
    }

    public void setVersionSize( String versionSize ) {
        this.versionSize = versionSize;
    }

    @Basic
    @Column( name = "RUNNING_PLATFORM", nullable = true, length = 30 )
    public String getRunningPlatform() {
        return runningPlatform;
    }

    public void setRunningPlatform( String runningPlatform ) {
        this.runningPlatform = runningPlatform;
    }

    @Basic
    @Column( name = "IS_DELETE", nullable = true, precision = 0 )
    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column( name = "CREATE_USER_ID", nullable = true, length = 20 )
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId( String createUserId ) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column( name = "UPDATE_USER_ID", nullable = true, length = 20 )
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId( String updateUserId ) {
        this.updateUserId = updateUserId;
    }

    @Basic
    @Column( name = "UPDATE_TIME", nullable = true )
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime( Date updateTime ) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column( name = "NEW_FEATURES", nullable = true, length = 50 )
    public String getNewFeatures() {
        return newFeatures;
    }

    public void setNewFeatures( String newFeatures ) {
        this.newFeatures = newFeatures;
    }

    @Basic
    @Column( name = "PACKAGE_NAME", nullable = true, length = 20 )
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName( String packageName ) {
        this.packageName = packageName;
    }

    @Basic
    @Column( name = "AUTH_DETAIL", nullable = true, length = 50 )
    public String getAuthDetail() {
        return authDetail;
    }

    public void setAuthDetail( String authDetail ) {
        this.authDetail = authDetail;
    }

    @Basic
    @Column( name = "LAST_VERSION", nullable = true, length = 100 )
    public String getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion( String lastVersion ) {
        this.lastVersion = lastVersion;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        MarketAppVersion that = ( MarketAppVersion ) o;
        return Objects.equals( appId, that.appId ) &&
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
                Objects.equals( updateTime, that.updateTime ) &&
                Objects.equals( newFeatures, that.newFeatures ) &&
                Objects.equals( packageName, that.packageName ) &&
                Objects.equals( authDetail, that.authDetail );
    }

    @Override
    public int hashCode() {

        return Objects.hash( appId, appVersion, appStatus, appDownloadAddress, createTime, versionInfo, versionSize, runningPlatform, isDelete, createUserId, updateUserId, updateTime, newFeatures, packageName, authDetail );
    }
}

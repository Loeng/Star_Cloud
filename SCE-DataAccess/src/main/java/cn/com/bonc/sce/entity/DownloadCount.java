package cn.com.bonc.sce.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@Entity
@Table( name = "SCE_MARKER_APP_DOWNLOAD", schema = "STARCLOUDMARKET", catalog = "" )
public class DownloadCount {
    private long id;
    private String appId;
    private Time downloadTime;
    private String userId;
    private String downloadUserIp;
    private Long isDelete = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column( name = "DOWNLOAD_TIME", nullable = true )
    public Time getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime( Time downloadTime ) {
        this.downloadTime = downloadTime;
    }

    @Basic
    @Column( name = "USER_ID", nullable = true, length = 20 )
    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    @Basic
    @Column( name = "DOWNLOAD_USER_IP", nullable = true, length = 10 )
    public String getDownloadUserIp() {
        return downloadUserIp;
    }

    public void setDownloadUserIp( String downloadUserIp ) {
        this.downloadUserIp = downloadUserIp;
    }

    @Basic
    @Column( name = "IS_DELETE", nullable = true, precision = 0 )
    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
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
                Objects.equals( isDelete, that.isDelete );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, appId, downloadTime, userId, downloadUserIp, isDelete );
    }
}

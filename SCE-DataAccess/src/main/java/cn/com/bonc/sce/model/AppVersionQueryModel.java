package cn.com.bonc.sce.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/22.
 */
@Entity
public class AppVersionQueryModel {
    private String appId;
    private String appName;
    private String appStatus;


    @Id
    @Column( name = "APP_ID", nullable = false, length = 30 )
    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    @Basic
    @Column( name = "APP_NAME", nullable = true, length = 50 )
    public String getAppName() {
        return appName;
    }

    public void setAppName( String appDownloadAddress ) {
        this.appName = appDownloadAddress;
    }

    @Basic
    @Column( name = "APP_STATUS", nullable = true, length = 30 )
    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus( String appStatus ) {
        this.appStatus = appStatus;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        AppVersionQueryModel that = ( AppVersionQueryModel ) o;
        return Objects.equals( appId, that.appId ) &&
                Objects.equals( appName, that.appName ) &&
                Objects.equals( appStatus, that.appStatus );
    }

    @Override
    public int hashCode() {

        return Objects.hash( appId, appName, appStatus );
    }
}

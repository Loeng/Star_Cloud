package cn.com.bonc.sce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/18.
 */
public class MarketAppVersionPK implements Serializable {
    private String appId;
    private String appVersion;

    @Column( name = "APP_ID", nullable = false, length = 30 )
    @Id
    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    @Column( name = "APP_VERSION", nullable = false, length = 30 )
    @Id
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion( String appVersion ) {
        this.appVersion = appVersion;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        MarketAppVersionPK that = ( MarketAppVersionPK ) o;
        return Objects.equals( appId, that.appId ) &&
                Objects.equals( appVersion, that.appVersion );
    }

    @Override
    public int hashCode() {

        return Objects.hash( appId, appVersion );
    }
}

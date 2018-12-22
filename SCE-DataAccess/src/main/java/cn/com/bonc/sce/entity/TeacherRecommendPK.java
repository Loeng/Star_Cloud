package cn.com.bonc.sce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/20.
 */
public class TeacherRecommendPK implements Serializable {
    private String appId;
    private String userId;

    @Column( name = "APP_ID", nullable = false, precision = 0, length = 20 )
    @Id
    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    @Column( name = "USER_ID", nullable = false, length = 20 )
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        TeacherRecommendPK that = ( TeacherRecommendPK ) o;
        return appId == that.appId &&
                Objects.equals( userId, that.userId );
    }

    @Override
    public int hashCode() {

        return Objects.hash( appId, userId );
    }
}

package cn.com.bonc.sce.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@Entity
@Table( name = "SCE_TEACHER_RECOMMEND_APP", schema = "STARCLOUDMARKET", catalog = "" )
@IdClass( TeacherRecommendPK.class )
public class TeacherRecommend implements Serializable {
    private String appId;
    private String userId;
    private Time recommendTime;
    private Long isDelete;
    private Time recommendStartTime;
    private Time recommendEndTime;

    @Id
    @Column( name = "APP_ID", nullable = false, precision = 0, length = 20 )
    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    @Id
    @Column( name = "USER_ID", nullable = false, length = 20 )
    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    @Basic
    @Column( name = "RECOMMEND_TIME", nullable = true )
    public Time getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime( Time recommendTime ) {
        this.recommendTime = recommendTime;
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
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TeacherRecommend that = ( TeacherRecommend ) o;
        return appId == that.appId &&
                Objects.equals( userId, that.userId ) &&
                Objects.equals( recommendTime, that.recommendTime ) &&
                Objects.equals( isDelete, that.isDelete );
    }

    @Override
    public int hashCode() {

        return Objects.hash( appId, userId, recommendTime, isDelete );
    }

    @Basic
    @Column( name = "RECOMMEND_START_TIME", nullable = true )
    public Time getRecommendStartTime() {
        return recommendStartTime;
    }

    public void setRecommendStartTime( Time recommendStartTime ) {
        this.recommendStartTime = recommendStartTime;
    }

    @Basic
    @Column( name = "RECOMMEND_END_TIME", nullable = true )
    public Time getRecommendEndTime() {
        return recommendEndTime;
    }

    public void setRecommendEndTime( Time recommendEndTime ) {
        this.recommendEndTime = recommendEndTime;
    }
}

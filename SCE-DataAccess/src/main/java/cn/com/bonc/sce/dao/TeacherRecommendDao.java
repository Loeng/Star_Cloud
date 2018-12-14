package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.TeacherRecommend;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TeacherRecommendDao {

    public boolean addTeacherRecommendApp( String appId, String teacherId, String recommendPeroid ) {
        return false;
    }

    public List< TeacherRecommend > selectRecommendAppListByTeacherId( String teacherId, String timePeroid ) {
        return null;
    }

    public Integer addTeacherRecommendAppList( List< String > appIdList, String teacherId, Map< String, Object > recommendPeroidMap ) {
        return null;
    }

    public Integer updateRecommendAppInfo( String teacherId, String appId, String recommendPeroid ) {
        return null;
    }

    public Integer deleteTeacherRecommendApp( String appId, String teacherId ) {
        return null;
    }

    public Integer deleteTeacherRecommendAppList( List< String > appIdList, String teacherId ) {
        return null;
    }
}

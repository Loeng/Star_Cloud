package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.TeacherRecommendDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TeacherRecommendService {
    private TeacherRecommendDao teacherRecommendDao;

    @Autowired
    public TeacherRecommendService( TeacherRecommendDao teacherRecommendDao ) {
        this.teacherRecommendDao = teacherRecommendDao;
    }

    public boolean addTeacherRecommendApp( String appId, String teacherId, String recommendPeroid ) {
        teacherRecommendDao.addTeacherRecommendApp(  appId,  teacherId,  recommendPeroid );
        return false;
    }

    public boolean addTeacherRecommendAppList( List< String > appIdList, String teacherId, Map< String, Object > recommendPeroidMap ) {
        teacherRecommendDao.addTeacherRecommendAppList( appIdList, teacherId, recommendPeroidMap );
        return false;
    }

    public boolean selectTeacherRecommendAppListByTeacherId( String teacherId, String timePeroid ) {
        teacherRecommendDao.selectRecommendAppListByTeacherId( teacherId, timePeroid );
        return false;
    }

    public boolean updateTeacherRecommendAppInfo( String teacherId, String appId, String recommendPeroid ) {
        teacherRecommendDao.updateRecommendAppInfo( teacherId, appId, recommendPeroid );
        return false;
    }

    public boolean deleteTeacherRecommendApp( String appId, String teacherId ) {
        teacherRecommendDao.deleteTeacherRecommendApp(appId, teacherId);
        return false;
    }

    public boolean deleteTeacherRecommendAppList( List<String> appIdList, String teacherId ) {
        teacherRecommendDao.deleteTeacherRecommendAppList(appIdList, teacherId);
        return false;
    }
}

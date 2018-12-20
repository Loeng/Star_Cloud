package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.TeacherRecommendDao;
import cn.com.bonc.sce.rest.RestRecord;
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


    public RestRecord addTeacherRecommendApp( String teacherId, List< Map< String, Object > > recommendPeroIdMap ) {
        return teacherRecommendDao.addTeacherRecommendApp( teacherId, recommendPeroIdMap );
    }

    public RestRecord updateTeacherRecommendAppInfo( String teacherId, List< Map< String, Object > > recommendPeroIdMap ) {
        teacherRecommendDao.updateTeacherRecommendAppInfo( teacherId, recommendPeroIdMap );
        return null;
    }

    public RestRecord deleteTeacherRecommendApp( String appId, List< String > teacherId ) {
        teacherRecommendDao.deleteTeacherRecommendApp( appId, teacherId );
        return null;
    }

    public RestRecord selectTeacherRecommendAppListByTeacherId( String teacherId, Map< String, Object > timePeroid, String pageNum, String pageSize ) {
        teacherRecommendDao.selectTeacherRecommendAppListByTeacherId( teacherId, timePeroid, pageNum, pageSize );
        return null;
    }
}

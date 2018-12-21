package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.TeacherRecommendDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by YueHaibo on 2018/12/12.
 */
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
        return teacherRecommendDao.updateTeacherRecommendAppInfo( teacherId, recommendPeroIdMap );

    }

    public RestRecord deleteTeacherRecommendApp( String teacherId, List< String > appIdList ) {
        return teacherRecommendDao.deleteTeacherRecommendApp( teacherId, appIdList );

    }

    public RestRecord selectTeacherRecommendAppList( String teacherId, String startTime, String endTime, int pageNum, int pageSize ) {
        return teacherRecommendDao.selectTeacherRecommendAppList( teacherId, startTime, endTime, pageNum, pageSize );

    }


}

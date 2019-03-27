package cn.com.bonc.sce.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by jc_D on 2019/3/6.
 */
public interface SchoolTeacherListMapper {


    List< Map > getTeacherListByOrganizationId( Map map );

    int deleteTeacher( String userId );

    int saveTeacher( Map map );

    int editTeacher( Map map );

    Long selectOrganizationIdByUserId( String userId );

}

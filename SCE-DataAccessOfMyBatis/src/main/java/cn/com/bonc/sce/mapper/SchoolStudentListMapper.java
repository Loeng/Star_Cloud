package cn.com.bonc.sce.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by jc_D on 2019/3/6.
 */
public interface SchoolStudentListMapper {


    List< Map > getStudentListByOrganizationId( Map map );

    int deleteStudent( String userId );

    int saveStudent( Map map );

    int editStudent( Map map );

    Long selectOrganizationIdByUserId( String userId );

}

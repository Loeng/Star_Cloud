package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.TeacherInfoBean;
import cn.com.bonc.sce.model.Institution;
import cn.com.bonc.sce.model.InstitutionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/14.
 */
public interface InstitutionMapper {

    int editInstitutionInfo(@Param("user_id") String user_id, @Param("work_time")Date work_time, @Param("entry_time") Date entry_time,
                                @Param("job_profession")String job_profession, @Param("work_number")String work_number);

    InstitutionInfo getInstitutionInfoByUserId(@Param("userId") String userId);

    int addInstitutionInfo(@Param("user_id") String user_id, @Param("work_time")Date work_time, @Param("entry_time") Date entry_time,
                            @Param("job_profession")String job_profession, @Param("work_number")String work_number,@Param("is_delete")Integer is_delete);

    int addInstitution(@Param("id") String id,@Param("institution_name") String institution_name,@Param("address") String address,
                       @Param("postcode") String postcode,@Param("province") String province,@Param("city") String city,@Param("district") String district,
                       @Param("institution_code") String institution_code,@Param("telephone") String telephone,@Param("email") String email,
                       @Param("homepage") String homepage,@Param("parent_institution") String parent_institution,@Param("is_delete") Integer is_delete);

    int updateInstitutionById(Institution institution);

    Institution getInstitutionById(@Param("id") String id);

    int updateInstitutionInfo(Institution institution);

}

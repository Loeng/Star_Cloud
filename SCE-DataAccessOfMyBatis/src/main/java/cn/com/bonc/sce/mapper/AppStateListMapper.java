package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author jc_D
 */
public interface AppStateListMapper {

    /**
     * 根据用户id 查询厂家id
     *
     * @param uid
     * @return
     */
    @Select( "SELECT COMPANY_ID FROM STARCLOUDPORTAL.SCE_INFO_COMPANY WHERE USER_ID = #{uid}" )
    Long getCompanyIdByUid( @Param( "uid" ) String uid );


    List< Map > getListByCondition(
            @Param( "companyId" ) Long companyId,
            @Param( "keyword" ) String keyword,
            @Param( "typeId" ) Integer typeId,
            @Param( "auditStatus" ) String auditStatus
    );

}

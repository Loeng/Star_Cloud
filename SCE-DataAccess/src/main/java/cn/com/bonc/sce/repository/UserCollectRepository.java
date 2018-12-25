package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional( rollbackFor = Exception.class )
public interface UserCollectRepository extends JpaRepository<App,String> {


    @Modifying
    @Query( value = "select T2.APP_NAME APP_NAME,T2.COMPANY_ID COMPANY_ID,T2.APP_NOTES APP_NOTES,T2.APP_PHONE_PIC APP_PHONE_PIC " +
            "from STARCLOUDMARKET.SCE_USER_APP_COLLECTION T1 left join STARCLOUDMARKET.SCE_MARKET_APP_INFO T2" +
            " on T1.APP_ID = T2.APP_ID " +
            "WHERE T1.IS_DELETE = 1 and T1.USER_ID = :userId   ", nativeQuery = true )
    List< Map< String, Object > > getUserAppCollect(@Param(value = "userId") String userId);

}

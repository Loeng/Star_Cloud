package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.DownloadCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by yinming on 2018/12/25.
 */
public interface UserOpenRepository extends JpaRepository< DownloadCount, String > {
    /**
     * 获取指定用户开通app列表
     * @param userId
     * @return
     */
    @Query( value = "select T1.OPEN_TIME OPEN_TIME,T2.APP_NAME APP_NAME,T1.APP_ID APP_ID,T2.APP_NOTES APP_NOTES,T2.APP_LINK APP_LINK,null APP_ICON from\n" +
            "  STARCLOUDMARKET.SCE_MARKET_APP_OPEN T1 left join STARCLOUDMARKET.SCE_MARKET_APP_INFO T2 on T1.APP_ID = T2.APP_ID\n" +
            "WHERE T1.IS_DELETE = 1 and T1.USER_ID = :userId" ,
            nativeQuery = true )
    List< Map< String,Object > >  getUserOpenList (@Param( value = "userId" ) String userId);
}

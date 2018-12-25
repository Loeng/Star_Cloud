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
 * @author BTW
 */
public interface UserDownloadRepository extends JpaRepository< DownloadCount, String > {

    /**
     * 获取指定用户下载app列表
     * @param pageable
     * @param userId
     * @return
     */
    @Query( value = "SELECT T1.APP_ID,T1.APP_NAME,T3.FILE_MAPPING_PATH FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T1,STARCLOUDPORTAL.SCE_FILE_RESOURCE T3 WHERE T1.APP_ICON = T3.RESOURCE_ID and EXISTS (SELECT * FROM STARCLOUDMARKET.SCE_MARKER_APP_DOWNLOAD T2 WHERE T2.APP_ID = T1.APP_ID AND T2.USER_ID = :userId AND T2.IS_DELETE = 1 )" ,
            countQuery = "SELECT COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T1 WHERE EXISTS (SELECT * FROM STARCLOUDMARKET.SCE_MARKER_APP_DOWNLOAD T2 WHERE T2.APP_ID = T1.APP_ID AND T2.USER_ID = :userId AND T2.IS_DELETE = 1 ) " ,
            nativeQuery = true )
    Page< List< Map< String,Object > > > getUserDownloadList ( @Param( value = "userId" ) String userId, Pageable pageable );
}

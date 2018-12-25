package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.DownloadCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
public interface AppCountRepository extends JpaRepository< DownloadCount, String > {

    /**
     * 查询全部应用下载量
     * @param pageable
     * @return
     */
    @Query( value = "SELECT T1.APP_NAME,(CASE WHEN T2.COUNT IS NULL THEN 0 ELSE T2.COUNT END) AS DOWNLOAD FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T1 LEFT OUTER JOIN (SELECT APP_ID,COUNT(*) AS COUNT FROM STARCLOUDMARKET.SCE_MARKER_APP_DOWNLOAD GROUP BY APP_ID) T2 ON T1.APP_ID = T2.APP_ID ORDER BY DOWNLOAD DESC" ,
            countQuery = "SELECT COUNT(*) FROM (SELECT T1.APP_NAME,(CASE WHEN T2.COUNT IS NULL THEN 0 ELSE T2.COUNT END) AS DOWNLOAD FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T1 LEFT OUTER JOIN (SELECT APP_ID,COUNT(*) AS COUNT FROM STARCLOUDMARKET.SCE_MARKER_APP_DOWNLOAD GROUP BY APP_ID) T2 ON T1.APP_ID = T2.APP_ID)" ,
            nativeQuery = true )
    Page <List< Map< String,Object > >> getAppDownloadRankingList ( Pageable pageable );
}

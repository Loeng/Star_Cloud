package cn.com.bonc.sce.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.bonc.sce.entity.AppTopRankView;
import java.util.List;
import java.util.Map;

/**
 * @author yanmin
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
public interface AppTopRankRepository extends JpaRepository< AppTopRankView, String >, JpaSpecificationExecutor<AppTopRankView>  {

    /**
     * @param userId
     * @param pageable
     * @return
     */
    @Query( value = "SELECT MAIN.APP_ID,MAIN.APP_NAME,MAIN.COMPANY_ID,MAIN.APP_ICON,MAIN.APP_NOTES,MAIN.APP_SOURCE,MAIN.APP_LINK,MAIN.CREATE_TIME,\n" +
            "CASE WHEN A.APP_ID IS NULL THEN '0' ELSE '1' END IS_OPEN,\n" +
            "CASE WHEN C.APP_ID IS NULL THEN '0' ELSE '1' END IS_DOWNLOAD,\n" +
            "CASE WHEN D.APP_ID IS NULL THEN '0' ELSE '1' END IS_COLLECT,\n" +
            "CASE WHEN T2.COUNT IS NULL THEN 0 ELSE T2.COUNT END AS DOWNLOAD\n" +
            "FROM (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_DELETE = 1 ) MAIN \n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN A ON A.APP_ID=MAIN.APP_ID AND A.USER_ID = :userId AND A.IS_DELETE=1\n" +
            "LEFT JOIN (SELECT B.APP_ID,COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD B WHERE B.USER_ID = :userId GROUP BY B.APP_ID) C ON C.APP_ID = MAIN.APP_ID\n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION D ON D.APP_ID=MAIN.APP_ID AND D.USER_ID = :userId AND D.IS_DELETE=1\n" +
            "LEFT JOIN (SELECT APP_ID,COUNT(*) AS COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD GROUP BY APP_ID) T2 ON MAIN.APP_ID = T2.APP_ID \n" +
            "INNER JOIN (SELECT  APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION WHERE APP_STATUS = 4 AND IS_DELETE =1  GROUP BY APP_ID ) V ON MAIN.APP_ID = V.APP_ID \n"+
            "ORDER BY MAIN.CREATE_TIME DESC",
            countQuery = "SELECT COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_DELETE = 1",
            nativeQuery = true )
    List< Map<String,String> > selectTopAppList( @Param( value = "userId" ) String userId,
                                                 Pageable pageable );

}

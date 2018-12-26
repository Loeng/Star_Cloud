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
public interface AppCountRepository extends JpaRepository< DownloadCount, String > {

    /**
     * 查询全部应用下载量
     *
     * @param pageable
     * @return
     */
    @Query( value = "SELECT T1.APP_NAME,(CASE WHEN T2.COUNT IS NULL THEN 0 ELSE T2.COUNT END) AS DOWNLOAD FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T1 LEFT OUTER JOIN (SELECT APP_ID,COUNT(*) AS COUNT FROM STARCLOUDMARKET.SCE_MARKER_APP_DOWNLOAD GROUP BY APP_ID) T2 ON T1.APP_ID = T2.APP_ID ORDER BY DOWNLOAD DESC",
            countQuery = "SELECT COUNT(*) FROM (SELECT T1.APP_NAME,(CASE WHEN T2.COUNT IS NULL THEN 0 ELSE T2.COUNT END) AS DOWNLOAD FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T1 LEFT OUTER JOIN (SELECT APP_ID,COUNT(*) AS COUNT FROM STARCLOUDMARKET.SCE_MARKER_APP_DOWNLOAD GROUP BY APP_ID) T2 ON T1.APP_ID = T2.APP_ID)",
            nativeQuery = true )
    Page< List< Map< String, Object > > > getAppDownloadRankingList( Pageable pageable );


    /**
     * 通过应用Id查询下载下载量
     *
     * @param appId 应用Id
     * @return
     */
    @Query( value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE APP_ID=:appId", nativeQuery = true )
    Page< List< Map< String, Object > > > getDownloadCountByAppId( @Param( "appId" ) String appId, Pageable pageable );

    @Query( value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE COMPANY_ID=:companyId", nativeQuery = true )
    Page< List< Map< String, Object > > > getDownloadCountByCompanyId( @Param( "companyId" ) Long companyId, Pageable pageable );

    @Query( value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE APP_TYPE_NAME=:type", nativeQuery = true )
    Page< List< Map< String, Object > > > getDownloadCountByType( @Param( "type" ) String type, Pageable pageable );

    @Query( value = "SELECT to_char ( t.DOWNLOAD_TIME, 'YYYY-MM' ) AS MONTH, \tsum( 1 ) AS \"COUNT\" \n" +
            "FROM \"STARCLOUDMARKET\".\"DOWNLOAD_CHANGE_VIEW\" t  WHERE \n" +
            "\tCOMPANY_ID=:companyId \n" +
            "\tand t.DOWNLOAD_TIME >= to_date ( :startTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "\tAND t.DOWNLOAD_TIME <= to_date ( :endTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "GROUP BY  to_char ( t.DOWNLOAD_TIME, 'YYYY-MM' ) \n" +
            "ORDER BY MONTH" ,nativeQuery = true)
    List< Map< String, Object > > getDownloadChange( @Param( "companyId" ) Long companyId,
                                                     @Param( "startTime" ) String startTime,
                                                     @Param( "endTime" ) String endTime );


    @Query( value = "SELECT SIC.USER_ID, SIC.COMPANY_ID, SMC.COMPANY_NAME, SMC.COMPANY_ADDRESS\n" +
            "FROM \"STARCLOUDPORTAL\".\"SCE_INFO_COMPANY\" SIC LEFT JOIN \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC ON SIC.COMPANY_ID=SMC.COMPANY_ID\n" +
            "WHERE USER_ID =:userId", nativeQuery = true )
    Map< String, Object > getCompanyInfo( @Param( "userId" ) String userId );
}

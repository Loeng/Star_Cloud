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
    @Query( value = "SELECT MAIN.APP_ID,MAIN.APP_NAME,MAIN.COMPANY_ID,MAIN.APP_ICON,MAIN.APP_NOTES,MAIN.APP_SOURCE,MAIN.APP_LINK,MAIN.CREATE_TIME, \n" +
            "            CASE WHEN A.APP_ID IS NULL THEN '0' ELSE '1' END IS_OPEN, \n" +
            "            CASE WHEN C.APP_ID IS NULL THEN '0' ELSE '1' END IS_DOWNLOAD, \n" +
            "            CASE WHEN D.APP_ID IS NULL THEN '0' ELSE '1' END IS_COLLECT,\n" +
            "            CASE WHEN T2.COUNT IS NULL THEN 0 ELSE T2.COUNT END AS DOWNLOAD\n" +
            "            FROM (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_DELETE = 1 ) MAIN  \n" +
            "            LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN A ON A.APP_ID=MAIN.APP_ID AND A.USER_ID = :userId AND A.IS_DELETE=1 \n" +
            "            LEFT JOIN (SELECT B.APP_ID,COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD B WHERE B.USER_ID = :userId GROUP BY B.APP_ID) C ON C.APP_ID = MAIN.APP_ID \n" +
            "            LEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION D ON D.APP_ID=MAIN.APP_ID AND D.USER_ID = :userId AND D.IS_DELETE=1 \n" +
            "            LEFT JOIN (SELECT APP_ID,COUNT(*) AS COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD GROUP BY APP_ID) T2 ON MAIN.APP_ID = T2.APP_ID \n" +
            "            INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
            "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
            "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID" +
            "            ORDER BY DOWNLOAD DESC",
            countQuery = "SELECT COUNT(*) " +
                    "            FROM (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_DELETE = 1 ) MAIN  \n" +
                    "            LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN A ON A.APP_ID=MAIN.APP_ID AND A.USER_ID = :userId AND A.IS_DELETE=1 \n" +
                    "            LEFT JOIN (SELECT B.APP_ID,COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD B WHERE B.USER_ID = :userId GROUP BY B.APP_ID) C ON C.APP_ID = MAIN.APP_ID \n" +
                    "            LEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION D ON D.APP_ID=MAIN.APP_ID AND D.USER_ID = :userId AND D.IS_DELETE=1 \n" +
                    "            LEFT JOIN (SELECT APP_ID,COUNT(*) AS COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD GROUP BY APP_ID) T2 ON MAIN.APP_ID = T2.APP_ID \n" +
                    "            INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
                    "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
                    "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID ",
            nativeQuery = true )
    Page< List< Map< String, Object > > > getAppDownloadRankingList( @Param( "userId" ) String userId,
                                                                     Pageable pageable );


    /**
     * 通过应用Id查询下载下载量
     *
     * @param appId    应用Id
     * @param pageable 分页参数
     * @return
     */
    @Query( value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE APP_ID=:appId", nativeQuery = true )
    Page< List< Map< String, Object > > > getDownloadCountByAppId( @Param( "appId" ) String appId, Pageable pageable );

    /**
     * 通过公司Id查询下载量
     *
     * @param companyId 公司Id
     * @param pageable  分页参数
     * @return
     */
    @Query( value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE COMPANY_ID=:companyId", nativeQuery = true )
    Page< List< Map< String, Object > > > getDownloadCountByCompanyId( @Param( "companyId" ) Long companyId, Pageable pageable );

    /**
     * 通过类型查询下载量
     *
     * @param type     类型名称
     * @param pageable 分页参数
     * @return
     */
    @Query( value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE APP_TYPE_NAME=:type", nativeQuery = true )
    Page< List< Map< String, Object > > > getDownloadCountByType( @Param( "type" ) String type, Pageable pageable );

    /**
     * 查询下载量变化(总量)
     *
     * @param companyId 厂商Id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Query( value = "SELECT to_char ( t.DOWNLOAD_TIME, 'YYYY-MM' ) AS MONTH, \tsum( 1 ) AS \"COUNT\" \n" +
            "FROM \"STARCLOUDMARKET\".\"DOWNLOAD_CHANGE_VIEW\" t  WHERE \n" +
            "\tCOMPANY_ID=:companyId \n" +
            "\tand t.DOWNLOAD_TIME >= to_date ( :startTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "\tAND t.DOWNLOAD_TIME <= to_date ( :endTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "GROUP BY  to_char ( t.DOWNLOAD_TIME, 'YYYY-MM' ) \n" +
            "ORDER BY MONTH", nativeQuery = true )
    List< Map< String, Object > > getDownloadChange( @Param( "companyId" ) Long companyId,
                                                     @Param( "startTime" ) String startTime,
                                                     @Param( "endTime" ) String endTime );

    /**
     * 查询下载量变化(单体应用)
     *
     * @param companyId 厂商Id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Query( value = "SELECT to_char ( t.DOWNLOAD_TIME, 'YYYY-MM' ) AS MONTH, \tsum( 1 ) AS \"COUNT\" \n" +
            "FROM \"STARCLOUDMARKET\".\"DOWNLOAD_CHANGE_VIEW\" t  WHERE \n" +
            "\tCOMPANY_ID=:companyId AND APP_ID=:appId \n" +
            "\tand t.DOWNLOAD_TIME >= to_date ( :startTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "\tAND t.DOWNLOAD_TIME <= to_date ( :endTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "GROUP BY  to_char ( t.DOWNLOAD_TIME, 'YYYY-MM' ) \n" +
            "ORDER BY MONTH", nativeQuery = true )
    List< Map< String, Object > > getDownloadChangeAndApp( @Param( "companyId" ) Long companyId,
                                                           @Param( "appId" ) String appId,
                                                           @Param( "startTime" ) String startTime,
                                                           @Param( "endTime" ) String endTime );

    /**
     * 通过userId查询到对应的厂商Id
     *
     * @param userId 用户Id
     * @return
     */
    @Query( value = "SELECT SIC.USER_ID, SIC.COMPANY_ID , SMC.COMPANY_NAME, SMC.COMPANY_ADDRESS\n" +
            "FROM \"STARCLOUDPORTAL\".\"SCE_INFO_COMPANY\" SIC LEFT JOIN \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC ON SIC.COMPANY_ID=SMC.COMPANY_ID\n" +
            "WHERE USER_ID =:userId", nativeQuery = true )
    Map< String, Object > getCompanyInfo( @Param( "userId" ) String userId );

    /**
     * 查询当月下载类型占比
     *
     * @param companyId 厂商Id
     * @param time      月份
     * @return
     */
    @Query( value = "SELECT\n" +
            "\t( CASE WHEN t.APP_TYPE_NAME IS NULL THEN '未知' ELSE t.APP_TYPE_NAME END ) AS APP_TYPE_NAME ,\n" +
            "\tcount( 1 ) AS COUNT \n" +
            "FROM\n" +
            "\t\"STARCLOUDMARKET\".\"DOWNLOAD_CHANGE_VIEW\" t \n" +
            "WHERE\n" +
            "\tCOMPANY_ID = :companyId \n" +
            "\tAND to_char ( t.DOWNLOAD_TIME, 'yyyy-mm' ) = :startTime \n" +
            "GROUP BY\n" +
            "\tt.APP_TYPE_NAME", nativeQuery = true )
    List< Map< String, Object > > getDownloadByType( @Param( "companyId" ) Long companyId,
                                                     @Param( "startTime" ) String startTime );

    @Query( value = "SELECT\n" +
            "            MAT.APP_TYPE_NAME,COUNT(MAT.APP_TYPE_NAME) AS APP_TYPE_COUNT\n" +
            "            FROM\n" +
            "            (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO WHERE IS_DELETE = 1) MAI \n" +
            "            LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY SMC ON SMC.COMPANY_ID = MAI.COMPANY_ID\n" +
            "            LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL MAAR ON MAAR.APP_ID = MAI.APP_ID\n" +
            "            LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE MAT ON MAAR.APP_TYPE_ID = MAT.APP_TYPE_ID\n" +
            "            WHERE SMC.COMPANY_ID=?1\n" +
            "            GROUP BY\n" +
            "            MAT.APP_TYPE_NAME", nativeQuery = true )
    List< Map< String, Object > > getAppTypePrecent( @Param( "companyId" ) int companyId );


    @Query( value = "\tSELECT  COUNT(COMPANY_ID) AS APP_COUNT  FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO \n" +
            "\tWHERE COMPANY_ID = ?1 AND IS_DELETE = 1 GROUP BY COMPANY_ID ", nativeQuery = true )
    Map< String, Object > getAppCountByCompanyId( @Param( "companyId" ) int companyId );

    /**
     * 查询收藏量变化(总量)
     *
     * @param companyId
     * @param startTime
     * @param endTime
     * @return
     */
    @Query( value = "SELECT to_char ( t.COLLECT_TIME, 'YYYY-MM' ) AS \"MONTH\", sum( 1 ) AS \"COUNT\" \n" +
            "FROM \"STARCLOUDMARKET\".\"COLLECTION_CHANGE_VIEW\" t  WHERE COMPANY_ID = :companyId \n" +
            "\tAND t.COLLECT_TIME >= to_date ( :startTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "\tAND t.COLLECT_TIME <= to_date ( :endTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "GROUP BY to_char ( t.COLLECT_TIME, 'YYYY-MM' )  ORDER BY \"MONTH\"", nativeQuery = true )
    List< Map< String, Object > > getCollectionChange( @Param( "companyId" ) Long companyId,
                                                       @Param( "startTime" ) String startTime,
                                                       @Param( "endTime" ) String endTime );


    /**
     * 查询收藏量变化(单个应用)
     *
     * @param companyId
     * @param startTime
     * @param endTime
     * @return
     */
    @Query( value = "SELECT to_char ( t.COLLECT_TIME, 'YYYY-MM' ) AS \"MONTH\", sum( 1 ) AS \"COUNT\" \n" +
            "FROM \"STARCLOUDMARKET\".\"COLLECTION_CHANGE_VIEW\" t  WHERE COMPANY_ID = :companyId AND APP_ID=:appId\n" +
            "\tAND t.COLLECT_TIME >= to_date ( :startTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "\tAND t.COLLECT_TIME <= to_date ( :endTime, 'yyyy-mm-dd hh24:mi:ss' ) \n" +
            "GROUP BY to_char ( t.COLLECT_TIME, 'YYYY-MM' )  ORDER BY \"MONTH\"", nativeQuery = true )
    List< Map< String, Object > > getCollectionChangeAndApp( @Param( "companyId" ) Long companyId,
                                                             @Param( "appId" ) String appId,
                                                             @Param( "startTime" ) String startTime,
                                                             @Param( "endTime" ) String endTime );


    @Query( value = "SELECT APP_NAME,APP_ID FROM \"STARCLOUDMARKET\".\"SCE_MARKET_APP_INFO\" WHERE COMPANY_ID=:companyId ORDER BY CREATE_TIME DESC", nativeQuery = true )
    List< Map< String, Object > > getCompanyAppList( @Param( "companyId" ) Long companyId );


    /**
     * 查询市场分析的接口
     */
    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE COMPANY_ID=:companyId AND APP_TYPE_ID=:appType" )
    Page< List< Map< String, Object > > > getDownloadList( @Param( "companyId" ) Long companyId,
                                                           @Param( "appType" ) int appType,
                                                           Pageable pageable );


    /**
     * 查询市场分析的接口（全部分类）
     */
    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.DOWNLOAD_COUNT_ANALYSIS_VIEW WHERE COMPANY_ID=:companyId" )
    Page< List< Map< String, Object > > > getDownloadAllList( @Param( "companyId" ) Long companyId,
                                                              Pageable pageable );

    @Query( value = "SELECT SMA.APP_NAME, SMT.APP_TYPE_NAME, 0 AS TIME\n" +
            "FROM\n" +
            "(SELECT APP_ID,COUNT(USER_ID) AS OPEN_COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_OPEN GROUP BY APP_ID ORDER BY OPEN_COUNT DESC) MAIN\n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_INFO SMA ON SMA.APP_ID = MAIN.APP_ID AND SMA.IS_DELETE=1\n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL SMR ON SMR.APP_ID = SMA.APP_ID\n" +
            "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE SMT ON SMT.APP_TYPE_ID = SMR.APP_TYPE_ID\n" +
            "INNER JOIN (SELECT  APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION WHERE APP_STATUS = 4 AND IS_DELETE =1  GROUP BY APP_ID ) SMV ON SMV.APP_ID = SMA.APP_ID\n" +
            "ORDER BY MAIN.OPEN_COUNT DESC\n", nativeQuery = true )
    Page< Map< String, Object > > getAppUseTimeRank( Pageable pageable );
}

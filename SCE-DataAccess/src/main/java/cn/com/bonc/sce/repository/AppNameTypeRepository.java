package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.AppInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

/**
 * @author BTW
 */
public interface AppNameTypeRepository extends JpaRepository< AppInfoEntity, String > {

    /**
     * @param pageable
     */
    @Query( value = "SELECT MAIN.APP_ID AS appId,MAIN.APP_NAME AS appName,MAIN.*,A5.APP_TYPE_NAME,A4.COMPANY_NAME FROM\n" +
            " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
            " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
            " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A1.APP_ID=A2.APP_ID) \n" +
            " AND A1.IS_DELETE=1) S1 \n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
            " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
            "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
            "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID \n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID\n" +
            " ORDER BY MAIN.CREATE_TIME DESC",
            countQuery = " SELECT COUNT(*) FROM\n" +
                    " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
                    " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
                    " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A1.APP_ID=A2.APP_ID) \n" +
                    " AND A1.IS_DELETE=1 ) S1 \n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
                    " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
                    "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
                    "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID \n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID",
            nativeQuery = true )
    Page< Map< String, Object > > selectAppList( Pageable pageable );

    /**
     * @param appName
     * @param pageable
     */
    @Query( value = "SELECT MAIN.*,A5.APP_TYPE_NAME,A4.COMPANY_NAME FROM\n" +
            " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
            " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
            " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A1.APP_ID=A2.APP_ID) \n" +
            " AND A1.IS_DELETE=1 AND A1.APP_NAME LIKE :appName) S1 \n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
            " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
            "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
            "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID\n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID\n" +
            " ORDER BY MAIN.CREATE_TIME DESC ",
            countQuery = " SELECT COUNT(*) FROM\n" +
                    " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
                    " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
                    " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A1.APP_ID=A2.APP_ID) \n" +
                    " AND A1.IS_DELETE=1 AND A1.APP_NAME LIKE :appName) S1 \n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
                    " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
                    "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
                    "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID\n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID",
            nativeQuery = true )
    Page< Map< String, Object > > selectAppListByName( @Param( value = "appName" ) String appName,
                                                       Pageable pageable );

    /**
     * @param appType
     * @param pageable
     */
    @Query( value = "SELECT MAIN.*,A5.APP_TYPE_NAME,A4.COMPANY_NAME FROM\n" +
            " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
            " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
            " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A2.APP_TYPE_ID = :appType AND A1.APP_ID=A2.APP_ID) \n" +
            " AND A1.IS_DELETE=1 ) S1 \n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
            " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
            "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
            "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID \n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID\n" +
            " ORDER BY MAIN.CREATE_TIME DESC ",
            countQuery = " SELECT COUNT(*) FROM\n" +
                    " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
                    " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
                    " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A2.APP_TYPE_ID = :appType AND A1.APP_ID=A2.APP_ID) \n" +
                    " AND A1.IS_DELETE=1 ) S1 \n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
                    " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
                    "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
                    "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID \n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID",
            nativeQuery = true )
    Page< Map< String, Object > > selectAppListByType( @Param( value = "appType" ) int appType,
                                                       Pageable pageable );

    /**
     * @param appName
     * @param appType
     * @param pageable
     */
    @Query( value = "SELECT MAIN.*,A5.APP_TYPE_NAME,A4.COMPANY_NAME FROM\n" +
            " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
            " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
            " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A2.APP_TYPE_ID = :appType AND A1.APP_ID=A2.APP_ID) \n" +
            " AND A1.IS_DELETE=1 AND A1.APP_NAME LIKE :appName) S1 \n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
            " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
            "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
            "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
            "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID \n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
            " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID\n" +
            " ORDER BY MAIN.CREATE_TIME DESC ",
            countQuery = " SELECT COUNT(*) FROM\n" +
                    " (SELECT S1.APP_ID,S1.APP_NAME,S1.COMPANY_ID,S1.APP_ICON,S1.CREATE_TIME,S1.IS_HOT_RECOMMEND,S1.IS_TOP_RECOMMEND,A3.APP_TYPE_ID FROM \n" +
                    " (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO A1 WHERE EXISTS \n" +
                    " (SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A2 WHERE A2.APP_TYPE_ID = :appType AND A1.APP_ID=A2.APP_ID) \n" +
                    " AND A1.IS_DELETE=1 AND A1.APP_NAME LIKE :appName) S1 \n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL A3 ON A3.APP_ID = S1.APP_ID ) MAIN\n" +
                    " INNER JOIN (SELECT AVC.APP_ID, AVC.APP_STATUS, TEMPA.CREATE_TIME CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC \n" +
                    "                       INNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME \n" +
                    "                       FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB  GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "                       AND TEMPA.CREATE_TIME = AVC.CREATE_TIME WHERE  APP_STATUS='4' AND IS_DELETE=1 \n" +
                    "                       ) TEMPB ON MAIN.APP_ID = TEMPB.APP_ID \n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_TYPE A5 ON A5.APP_TYPE_ID = MAIN.APP_TYPE_ID\n" +
                    " LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY A4 ON A4.COMPANY_ID = MAIN.COMPANY_ID",
            nativeQuery = true )
    Page< Map< String, Object > > selectAppListByNameAndType( @Param( value = "appName" ) String appName,
                                                              @Param( value = "appType" ) int appType,
                                                              Pageable pageable );

}

package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.DownloadCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    @Query( value = "SELECT T1.APP_ID,T1.APP_NAME,T1.FILE_MAPPING_PATH,T2.DOWNLOAD_TIME FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD T2 LEFT OUTER JOIN (SELECT T3.FILE_MAPPING_PATH,T4.APP_ID,T4.APP_NAME FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T4, STARCLOUDPORTAL.SCE_FILE_RESOURCE T3 WHERE T3.RESOURCE_ID=T4.APP_ICON) T1 ON T1.APP_ID = T2.APP_ID WHERE T2.USER_ID = :userId AND T2.IS_DELETE=1 ORDER BY T2.DOWNLOAD_TIME DESC" ,
            countQuery = "SELECT COUNT(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD T2 LEFT OUTER JOIN (SELECT T3.FILE_MAPPING_PATH,T4.APP_ID,T4.APP_NAME FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO T4, STARCLOUDPORTAL.SCE_FILE_RESOURCE T3 WHERE T3.RESOURCE_ID=T4.APP_ICON) T1 ON T1.APP_ID = T2.APP_ID WHERE T2.USER_ID = :userId AND T2.IS_DELETE=1" ,
            nativeQuery = true )
    Page< List< Map< String,Object > > > getUserDownloadList ( @Param( value = "userId" ) String userId, Pageable pageable );

    /**
     * 添加下载记录
     * @param s
     * @param <S>
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    @Modifying
    @Override
    < S extends DownloadCount > S save( S s );


    /**
     *@Author : lyy
     *@Desc: 统计用户表中每个角色的数量
     *@Date : 15:44 2018/12/27
     */
    @Query(value =  "SELECT UR.ROLE_NAME,COUNT(U.USER_ID) AS USERGOUPCOUN\n" +
            "        FROM STARCLOUDPORTAL.SCE_COMMON_USER U LEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER_ROLE_REL URR ON URR.USER_ID = U.USER_ID LEFT JOIN  STARCLOUDPORTAL.SCE_COMMON_USER_ROLE UR ON UR.ROLE_ID = URR.ROLE_ID\n" +
            "        GROUP BY UR.ROLE_NAME",nativeQuery = true)
    List<Map<String,Object>> getUserGroupCount();

    /**
     *@Author : lyy
     *@Desc :  统计应用下载表的中未删除的app数量
     *@Date : 15:50 2018/12/27
     */
    @Query(value = "select COUNT(ID) AS APP_COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD\n" +
            "WHERE IS_DELETE = 1",nativeQuery = true)
    Map<String,Object> getAppCount();
}

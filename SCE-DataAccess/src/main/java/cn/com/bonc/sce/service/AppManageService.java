package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.entity.AppTypeRelEntity;
import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.model.AppAddModel;
import cn.com.bonc.sce.model.AppTypeMode;
import cn.com.bonc.sce.model.PlatFormAddModel;
import cn.com.bonc.sce.repository.*;
import cn.com.bonc.sce.rest.RestRecord;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author jc_D
 * @description
 * @date 2019/1/3
 **/
@Slf4j
@Service

public class AppManageService {
    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private AppInfoRepository appInfoRepository;
    @Autowired
    private AppTypeRelRepository appTypeRelRepository;

    @Autowired
    private MarketAppVersionRepository marketAppVersionRepository;

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Transactional( rollbackFor = Exception.class )
    public RestRecord addAppInfo( AppAddModel appInfo, String uid ) {
        //根据uid查companyId
        Long companyId = companyInfoRepository.getCompanyIdByUid( uid );

        //取icon
        Integer iconId = appInfo.getAppIcon();
        Map< String, Object > iconAd = fileResourceRepository.getFileStorePathById( iconId );
        String iconAddress = iconAd.get( "FILE_STORE_PATH" ).toString();
        //1.appinfo表
        AppInfoEntity appInfoEntity = new AppInfoEntity();
        appInfoEntity.setAppIcon( iconAddress );
        appInfoEntity.setAppName( appInfo.getAppName() );
        appInfoEntity.setCreateUserId( uid );
        appInfoEntity.setCreateTime( new Date() );
        appInfoEntity.setIsDelete( 1 );
        appInfoEntity.setAppSource( "rj" );
        appInfoEntity.setAppNotes( appInfo.getAppNotes() );
        appInfoEntity.setCompanyId( companyId );
        AppInfoEntity info = appInfoRepository.saveAndFlush( appInfoEntity );
        String appId = info.getAppId();
        //2.类型关系表
        AppTypeRelEntity appTypeRelEntity = new AppTypeRelEntity();
        appTypeRelEntity.setAppId( appId );
        appTypeRelEntity.setAppTypeId( appInfo.getAppTypeId() );
        AppTypeRelEntity rel = appTypeRelRepository.saveAndFlush( appTypeRelEntity );

        //根据id取pc图片链接
        String pcUrl = getFilesUrlById( appInfo.getAppPcPic() );

        //根据id取phone图片链接
        String phoneUrl = getFilesUrlById( appInfo.getAppPhonePic() );

        //3.版本表
        Set< AppTypeMode > pcSet = appInfo.getPc();
        pcSet.forEach( pc -> {
            String version = pc.getAppVersion();
            //根据addressId获取软件存储路径
            String addressId = pc.getAddress();
            Map< String, Object > ad = fileResourceRepository.getFileStorePathById( Integer.parseInt( addressId ) );
            String softwareAddress = ad.get( "FILE_STORE_PATH" ).toString();
            //往版本表存东西
            MarketAppVersion marketAppVersion = new MarketAppVersion();
            marketAppVersion.setAppId( appId );
            marketAppVersion.setAppDownloadAddress( softwareAddress );
            marketAppVersion.setAppVersion( version );
            marketAppVersion.setVersionInfo( appInfo.getAppNotes() );
            marketAppVersion.setPackageName( pc.getPackageName() );
            marketAppVersion.setVersionSize( pc.getVersionSize() );
            marketAppVersion.setAppStatus( "1" );
            marketAppVersion.setNewFeatures( appInfo.getNewFeatures() );
            marketAppVersion.setAuthDetail( appInfo.getAuthDetail().toString().replace( "[", "" ).replace( "]", "" ).replaceAll( "\\s*", "" ) );
            marketAppVersion.setAppPcPic( pcUrl );
            marketAppVersion.setAppPhonePic( phoneUrl );
            marketAppVersion.setCreateTime( new Date() );
            marketAppVersion.setIsDelete( 1L );
            marketAppVersion.setRunningPlatform( pc.getRunningPlatform() );
            marketAppVersion.setCreateUserId( uid );
            marketAppVersionRepository.saveAndFlush( marketAppVersion );
        } );

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appId );
    }

    @Transactional( rollbackFor = Exception.class )
    public RestRecord addPlatFormInfo( PlatFormAddModel platFormInfo, String uid ) {
        //根据uid查companyId
        Long companyId = companyInfoRepository.getCompanyIdByUid( uid );
        //取icon
        Integer iconId = platFormInfo.getAppIcon();
        Map< String, Object > iconAd = fileResourceRepository.getFileStorePathById( iconId );
        String iconAddress = iconAd.get( "FILE_STORE_PATH" ).toString();
        //1.appinfo表
        AppInfoEntity appInfoEntity = new AppInfoEntity();
        appInfoEntity.setAppIcon( iconAddress );
        appInfoEntity.setAppName( platFormInfo.getAppName() );
        appInfoEntity.setCreateUserId( uid );
        appInfoEntity.setCreateTime( new Date() );
        appInfoEntity.setIsDelete( 1 );
        appInfoEntity.setAppSource( "pt" );
        appInfoEntity.setAppNotes( platFormInfo.getAppNotes() );
        appInfoEntity.setCompanyId( companyId );
        AppInfoEntity info = appInfoRepository.saveAndFlush( appInfoEntity );
        String appId = info.getAppId();
        //2.类型关系表
        AppTypeRelEntity appTypeRelEntity = new AppTypeRelEntity();
        appTypeRelEntity.setAppId( appId );
        appTypeRelEntity.setAppTypeId( platFormInfo.getAppTypeId() );
        AppTypeRelEntity rel = appTypeRelRepository.saveAndFlush( appTypeRelEntity );

        //根据id取pc图片链接
        String pcUrl = getFilesUrlById( platFormInfo.getAppPcPic() );

        //3.版本表
        //根据addressId获取软件存储路径
        Integer storeLocation = platFormInfo.getStoreLocation();
        Map< String, Object > ptStoreLocation = fileResourceRepository.getFileStorePathById( storeLocation );
        String platFormAddress = ptStoreLocation.get( "FILE_STORE_PATH" ).toString();
        //往版本表存东西
        MarketAppVersion marketAppVersion = new MarketAppVersion();
        marketAppVersion.setAppId( appId );
        marketAppVersion.setAppDownloadAddress( platFormAddress );
        marketAppVersion.setAppVersion( platFormInfo.getAppVersion() );
        marketAppVersion.setVersionInfo( platFormInfo.getAppNotes() );
        marketAppVersion.setPackageName( platFormInfo.getPackageName() );
        marketAppVersion.setAppStatus( "1" );
        marketAppVersion.setNewFeatures( platFormInfo.getNewFeatures() );
        marketAppVersion.setAppPcPic( pcUrl );
        marketAppVersion.setCreateTime( new Date() );
        marketAppVersion.setIsDelete( 1L );
        marketAppVersion.setCreateUserId( uid );
        marketAppVersion.setIndexUrl( platFormInfo.getIndexUrl() );
        marketAppVersion.setInstallInfo( platFormInfo.getInstallInfo() );
        marketAppVersion.setStoreLocation( platFormAddress );
        marketAppVersion.setTestUrl( platFormInfo.getTestUrl() );
        marketAppVersionRepository.saveAndFlush( marketAppVersion );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appId );
    }


    /**
     * 根据文件id取文件路径
     *
     * @param ids 一批文件id
     * @return
     */
    private String getFilesUrlById( Set< Integer > ids ) {
        if ( CollUtil.isEmpty( ids ) ) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for ( Integer id : ids ) {
            Map< String, Object > fileStorePath = fileResourceRepository.getFileStorePathById( id );
            String p = fileStorePath.get( "FILE_STORE_PATH" ).toString();
            sb.append( p ).append( "," );
        }
        return StringUtils.substring( sb.toString(), 0, sb.length() - 1 );
    }

}

package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.DownloadCount;
import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.repository.AppVersionRepository;
import cn.com.bonc.sce.repository.FileResourceRepository;
import cn.com.bonc.sce.repository.UserDownloadRepository;
import cn.com.bonc.sce.rest.RestRecord;
import com.sun.org.apache.xpath.internal.operations.Number;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class AppDownloadService {
    private AppVersionRepository appVersionRepository;
    private UserDownloadRepository userDownloadRepository;
    private FileResourceRepository fileResourceRepository;

    @Autowired
    public AppDownloadService( AppVersionRepository appVersionRepository, UserDownloadRepository userDownloadRepository, FileResourceRepository fileResourceRepository){
        this.appVersionRepository = appVersionRepository;
        this.userDownloadRepository = userDownloadRepository;
        this.fileResourceRepository = fileResourceRepository;
    }

    public RestRecord getAppDownloadPath( String userId, String appId, String version, String platform ) {
        DownloadCount downloadCount = new DownloadCount();
        downloadCount.setUserId( userId );
        downloadCount.setAppId( appId );
        userDownloadRepository.save( downloadCount );

        String downloadId = appVersionRepository.getDownloadAddressByIdAndVersionAndName( appId, version, platform );
        //Todo : 根据资源id去资源表获取下载路径
        return new RestRecord( 200, downloadId );
    }
}

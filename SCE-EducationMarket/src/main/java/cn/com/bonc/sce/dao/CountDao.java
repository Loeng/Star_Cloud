package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.DownloadCount;
import org.springframework.stereotype.Repository;

@Repository
public class CountDao {
    public DownloadCount countSingleAppDownload( String appId ) {
        return null;
    }

    public DownloadCount countAppDownloadByType( String appType ) {
        return null;
    }

    public DownloadCount countAppDownloadByCompany( String companyId ) {
        return null;
    }
}

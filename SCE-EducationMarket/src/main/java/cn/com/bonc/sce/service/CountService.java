package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.CountDao;
import cn.com.bonc.sce.model.DownloadCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CountService {
    private CountDao countDao;

    @Autowired
    public CountService( CountDao countDao ) {
        this.countDao = countDao;
    }

    public DownloadCount countSingleAppDownload( String appId ) {
        return countDao.countSingleAppDownload( appId );
    }

    public DownloadCount countAppDownloadByType( String appType ) {
        return countDao.countAppDownloadByType( appType );
    }

    public DownloadCount countAppDownloadByCompany( String companyId ) {
        return countDao.countAppDownloadByCompany( companyId );
    }
}

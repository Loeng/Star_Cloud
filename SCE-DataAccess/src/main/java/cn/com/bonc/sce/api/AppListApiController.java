package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.AppListDao;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/appList" )
public class AppListApiController {
    private AppListDao appListDao;

    @Autowired
    public AppListApiController( AppListDao appListDao ) {
        this.appListDao = appListDao;
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @GetMapping( "/{classId}/{keyword}" )
    @ResponseBody
    public RestRecord getAppListInfo(@PathVariable( "appClassId" )Integer appClassId,
                                         @PathVariable( "keyword" )String keyword) {
        try{
            return new RestRecord(200,appListDao.getAppListInfo(appClassId,keyword));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }
}


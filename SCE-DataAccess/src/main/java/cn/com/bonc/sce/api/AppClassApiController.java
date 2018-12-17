package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.AppClassDao;
import cn.com.bonc.sce.model.AppClass;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/banners" )
public class AppClassApiController {
    private AppClassDao appClassDao;

    @Autowired
    public AppClassApiController( AppClassDao appClassDao ) {
        this.appClassDao = appClassDao;
    }

    /**
     * 添加应用分类
     *
     * @param appClass 信息
     * @return 是否添加成功
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertAppClass( AppClass appClass ) {
        try{
            return new RestRecord(appClassDao.insertAppClass( appClass ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 通过id删除应用分类
     *
     * @param appClassId id
     * @return 删除是否成功
     */
    @DeleteMapping( "/{appClassId}" )
    @ResponseBody
    public RestRecord deleteAppClassById( @PathVariable( "appClassId" ) Integer appClassId ) {
        try{
            return new RestRecord(200,appClassDao.deleteAppClassById( appClassId ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 更新应用分类
     *
     * @param appClass 应用分类信息
     * @return appClass
     */
    @PutMapping( "" )
    @ResponseBody
    public RestRecord updateAppClassInfo( AppClass appClass ) {
        try{
            return new RestRecord(200,appClassDao.updateAppClassInfo( appClass ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAllAppClassInfo() {
        try{
            return new RestRecord(200,appClassDao.getAllAppClassInfo());
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }
}


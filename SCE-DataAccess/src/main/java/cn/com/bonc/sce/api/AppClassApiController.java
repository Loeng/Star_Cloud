package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AppClassDao;
import cn.com.bonc.sce.model.appListAndClass.AppClass;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/17/12 12:00
 */
@Slf4j
@RestController
@RequestMapping( "/appClass" )
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
            return new RestRecord(200,appClassDao.save( appClass ));
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(409,MessageConstants.SCE_MSG_409, e);
        }
    }

    /**
     * 通过id删除应用分类
     *
     * @param classId id
     * @return 删除是否成功
     */
    @DeleteMapping( "/{classId}" )
    @ResponseBody
    public RestRecord deleteAppClassById( @PathVariable( "classId" ) Integer classId ) {
        try{
            appClassDao.updateDeleteStatusById( classId );
            return new RestRecord(200);
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(408,MessageConstants.SCE_MSG_408, e);
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
            return new RestRecord(200,appClassDao.save( appClass ));
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(407, MessageConstants.SCE_MSG_407, e);
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
            return new RestRecord(200,appClassDao.findByIsDelete(0));
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(406,MessageConstants.SCE_MSG_406, e);
        }
    }
}


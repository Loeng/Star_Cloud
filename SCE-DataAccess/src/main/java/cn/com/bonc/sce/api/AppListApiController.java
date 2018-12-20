package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AppListDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 应用列表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
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
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAppListInfo() {
        try{
            return new RestRecord(200,appListDao.findByIsDelete(0));
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(406, MessageConstants.SCE_MSG_406, e);
        }
    }

    /**
     * 获取所有应用分类数据
     *
     * @param classId 应用分类信息
     * @return 应用分类数据list
     */
    @GetMapping( "/classId/{classId}" )
    @ResponseBody
    public RestRecord getAppListInfo(@PathVariable( value="classId")Integer classId) {
        try{
            return new RestRecord(200,appListDao.findByCategoryIdAndIsDelete(classId,0));
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(406,MessageConstants.SCE_MSG_406, e);
        }
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @GetMapping( "/keyword/{keyword}" )
    @ResponseBody
    public RestRecord getAppListInfo(@PathVariable( value="keyword")String keyword) {
        try{
                return new RestRecord(200,appListDao.findByAppNameLikeAndIsDelete("%"+keyword+"%",0));
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(406,MessageConstants.SCE_MSG_406, e);
        }
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @GetMapping( "/{classId}/{keyword}" )
    @ResponseBody
    public RestRecord getAppListInfo(@PathVariable( value="classId")Integer classId,
                                     @PathVariable( value="keyword")String keyword) {
        try{
            return new RestRecord(200,appListDao.findByCategoryIdAndAppNameLikeAndIsDelete(classId,"%"+keyword+"%",0));
        }catch ( Exception e ){
            log.error( e.getMessage(),e );
            return new RestRecord(406,MessageConstants.SCE_MSG_406, e);
        }
    }
}


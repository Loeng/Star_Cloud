package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.HistoryAdviseDao;
import cn.com.bonc.sce.model.HistoryAdvise;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/historyAdvises" )
public class HistoryAdviseApiController {
    private HistoryAdviseDao historyAdviseDao;

    @Autowired
    public HistoryAdviseApiController( HistoryAdviseDao historyAdviseDao ) {
        this.historyAdviseDao = historyAdviseDao;
    }

    /**
     * 添加historyAdvise
     *
     * @param historyAdvise 信息
     * @return 是否添加成功
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertHistoryAdvise( HistoryAdvise historyAdvise ) {
        try{
            return new RestRecord(historyAdviseDao.insertHistoryAdvise( historyAdvise ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 通过id删除historyAdvise
     *
     * @param historyAdviseId  id
     * @return 删除是否成功
     */
    @DeleteMapping( "/{historyAdviseId}" )
    @ResponseBody
    public RestRecord deleteHistoryAdviseById( @PathVariable( "historyAdviseId" ) String historyAdviseId ) {
        try{
            return new RestRecord(200,historyAdviseDao.deleteHistoryAdviseById( historyAdviseId ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 更新historyAdvise
     *
     * @param historyAdvise historyAdvise信息
     * @return historyAdvise
     */
    @PutMapping( "" )
    @ResponseBody
    public RestRecord updateHistoryAdviseInfo( HistoryAdvise historyAdvise ) {
        try{
            return new RestRecord(200,historyAdviseDao.updateHistoryAdviseInfo( historyAdvise ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 修改url
     *
     * @param historyAdviseId   historyAdviseId
     * @param url 待修改的url
     * @return 跟新是否成功
     */
    @PatchMapping( "/{historyAdviseId}" )
    @ResponseBody
    public RestRecord updateHistoryAdviseUrl(
            @PathVariable( "historyAdviseId" ) String historyAdviseId,
            @RequestParam( "url" ) String url ) {
        try{
            return new RestRecord(200,historyAdviseDao.updateHistoryAdviseUrl( historyAdviseId, url ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 获取historyAdvise数据
     *
     * @param historyAdviseId historyAdviseId
     * @return historyAdvise数据
     */
    @GetMapping( "/{historyAdviseId}" )
    @ResponseBody
    public RestRecord getHistoryAdviseById( @PathVariable( "historyAdviseId" ) String historyAdviseId ) {
        try{
            return new RestRecord(200,historyAdviseDao.getHistoryAdviseById( historyAdviseId));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 获取所有historyAdvise数据
     *
     * @return historyAdvise数据list
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAllHistoryAdvisesInfo() {
        try{
            return new RestRecord(200,historyAdviseDao.getAllHistoryAdvisesInfo());
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }
}

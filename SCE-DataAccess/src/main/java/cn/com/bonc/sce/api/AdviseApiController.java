package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.AdviseDao;
import cn.com.bonc.sce.model.Advise;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/advises" )
public class AdviseApiController {
    private AdviseDao adviseDao;

    @Autowired
    public AdviseApiController( AdviseDao adviseDao ) {
        this.adviseDao = adviseDao;
    }

    /**
     * 添加advise
     *
     * @param advise 信息
     * @return 是否添加成功
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertAdvise( Advise advise ) {
        try{
            return new RestRecord(adviseDao.insertAdvise( advise ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 通过id删除advise
     *
     * @param adviseId  id
     * @return 删除是否成功
     */
    @DeleteMapping( "/{adviseId}" )
    @ResponseBody
    public RestRecord deleteAdviseById( @PathVariable( "adviseId" ) String adviseId ) {
        try{
            return new RestRecord(200,adviseDao.deleteAdviseById( adviseId ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 更新advise
     *
     * @param advise advise信息
     * @return advise
     */
    @PutMapping( "" )
    @ResponseBody
    public RestRecord updateAdviseInfo( Advise advise ) {
        try{
            return new RestRecord(200,adviseDao.updateAdviseInfo( advise ));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 获取advise数据
     *
     * @param adviseId adviseId
     * @return advise数据
     */
    @GetMapping( "/{adviseId}" )
    @ResponseBody
    public RestRecord getAdviseById( @PathVariable( "adviseId" ) String adviseId ) {
        try{
            return new RestRecord(200,adviseDao.getAdviseById( adviseId));
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }

    /**
     * 获取所有advise数据
     *
     * @return advise数据list
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAllAdvisesInfo() {
        try{
            return new RestRecord(200,adviseDao.getAllAdvisesInfo());
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }
}

package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.entity.ColumnInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ColumnInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 栏目接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Slf4j
@RestController
@RequestMapping( "/columns" )
public class ColumnInfoApiController {
    @Autowired
    private ColumnInfoService columnInfoService;

    /**
     * 添加栏目
     *
     * @param columnInfo 添加栏目
     * @return 是否添加成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord insert( @RequestBody ColumnInfo columnInfo ) {
        try {
            return columnInfoService.insert( columnInfo );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @DeleteMapping( "/{id}" )
    @ResponseBody
    public RestRecord deleteById( @PathVariable( "id" ) Integer id ) {
        try {
            return columnInfoService.deleteById( id );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 408, MessageConstants.SCE_MSG_408, e );
        }
    }

    /**
     * 更新栏目
     *
     * @param columnInfo 栏目
     * @return columnInfo
     */
    @PutMapping
    @ResponseBody
    public RestRecord updateInfo( @RequestBody ColumnInfo columnInfo ) {
        try {
            return columnInfoService.updateInfo( columnInfo );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 获取所有栏目数据
     *
     * @return 栏目数据list
     */
    @GetMapping
    @ResponseBody
    public RestRecord getAll() {
        try {
            return columnInfoService.getAll();
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}

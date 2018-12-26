package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.entity.ChannelInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ChannelInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 频道接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Slf4j
@RestController
@RequestMapping( "/channels" )
public class ChannelInfoApiController {

    @Autowired
    private ChannelInfoService channelInfoService;

    /**
     * 添加频道
     *
     * @param channelInfo 添加频道
     * @return 是否添加成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord insert( @RequestBody ChannelInfo channelInfo ) {
        try {
            return channelInfoService.insert( channelInfo );
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
            return channelInfoService.deleteById( id );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 408, MessageConstants.SCE_MSG_408, e );
        }
    }

    /**
     * 更新频道
     *
     * @param channelInfo 频道
     * @return channelInfo
     */
    @PutMapping
    @ResponseBody
    public RestRecord updateInfo( @RequestBody ChannelInfo channelInfo ) {
        try {
            return channelInfoService.updateInfo( channelInfo );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 获取所有频道数据
     *
     * @return 频道数据list
     */
    @GetMapping
    @ResponseBody
    public RestRecord getAll() {
        try {
            return channelInfoService.getAll();
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}

package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.BannerDao;
import cn.com.bonc.sce.entity.Banner;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * banner接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@RestController
@RequestMapping( "/banners" )
public class BannerApiController {
    @Autowired
    private BannerDao bannerDao;

    /**
     * 添加banner
     *
     * @param banner 信息
     * @return 是否添加成功
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertBanner( Banner banner ) {
        try {
            return new RestRecord( 200, bannerDao.save( banner ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 通过id删除banner
     *
     * @param bannerId id
     * @return 删除是否成功
     */
    @DeleteMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord deleteBannerById( @PathVariable( "bannerId" ) Integer bannerId ) {
        try {
            return new RestRecord( 200, bannerDao.updateDeleteStatusById( bannerId ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 408, MessageConstants.SCE_MSG_408, e );
        }
    }

    /**
     * 更新banner
     *
     * @param banner banner信息
     * @return banner
     */
    @PutMapping( "" )
    @ResponseBody
    public RestRecord updateBannerInfo( Banner banner ) {
        try {
            return new RestRecord( 200, bannerDao.save( banner ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 修改url
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    @PutMapping( "/url" )
    @ResponseBody
    public RestRecord updateBannerUrl(Banner banner) {
        try {
            return new RestRecord( 200, bannerDao.updateUrlById( banner.getId(), banner.getUrl() ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 修改appId
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    @PutMapping( "/appId" )
    @ResponseBody
    public RestRecord updateBannerAppId(Banner banner) {
        try {
            return new RestRecord( 200, bannerDao.updateAppIdById( banner.getId(), banner.getAppId() ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 修改轮播次序
     *
     * @param list bannerId
     * @return 修改结果
     */
    @PutMapping( "/bannerOrder" )
    @ResponseBody
    public RestRecord updateBannerOrder( @RequestParam( "list" ) List< Integer > list ) {
        Integer total = 0;
        Integer size = 0;
        if ( list != null ){
            size = list.size();
        }
        try {
            for ( int i = 0; i < size; i++ ) {
                total += bannerDao.updateOrderById( list.get( i ), i+1 );
            }
            return new RestRecord( 200, total );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 获取banner数据
     *
     * @param bannerId bannerId
     * @return banner数据
     */
    @GetMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord getBannerById( @PathVariable( "bannerId" ) Integer bannerId ) {
        try {
            return new RestRecord( 200, bannerDao.findByIdAndIsDelete( bannerId,0 ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }

    /**
     * 获取所有banner数据
     *
     * @return banner数据list
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAllBannersInfo() {
        try {
            return new RestRecord( 200, bannerDao.findByIsDelete(0) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}


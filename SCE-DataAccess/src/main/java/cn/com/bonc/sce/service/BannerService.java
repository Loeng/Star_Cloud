package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.BannerDao;
import cn.com.bonc.sce.entity.Banner;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * banner接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class BannerService {
    @Autowired
    private BannerDao bannerDao;

    /**
     * 添加banner
     *
     * @param banner 信息
     * @return 是否添加成功
     */
    public RestRecord insertBanner( Banner banner ) {
        banner.setId( null );
        banner.setIsDelete( 1 );
        return new RestRecord( 200, bannerDao.save( banner ) );
    }

    /**
     * 通过id删除banner
     *
     * @param bannerId id
     * @return 删除是否成功
     */
    public RestRecord deleteBannerById( Integer bannerId ) {
        return new RestRecord( 200, bannerDao.updateDeleteStatusById( bannerId ) );
    }

    /**
     * 更新banner
     *
     * @param banner banner信息
     * @return banner
     */
    public RestRecord updateBannerInfo( Banner banner ) {
        banner.setIsDelete( 0 );
        return new RestRecord( 200, bannerDao.save( banner ) );
    }

    /**
     * 修改url
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    public RestRecord updateBannerUrl( Banner banner ) {
        return new RestRecord( 200, bannerDao.updateUrlById( banner.getId(), banner.getUrl() ));
    }

    /**
     * 修改appId
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    public RestRecord updateBannerAppId( Banner banner ) {
        return new RestRecord( 200, bannerDao.updateAppIdById( banner.getId(), banner.getAppId() ) );
    }

    /**
     * 修改轮播次序
     *
     * @param list bannerId
     * @return 修改结果
     */
    public RestRecord updateBannerOrder( List< Integer > list ) {
        Integer total = 0;
        Integer size = 0;
        if ( list != null ) {
            size = list.size();
        }
        for ( int i = 0; i < size; i++ ) {
            total += bannerDao.updateOrderById( list.get( i ), i + 1 );
        }
        return new RestRecord( 200, total );
    }

    /**
     * 获取banner数据
     *
     * @param bannerType bannerType
     * @return banner数据
     */
    public RestRecord getBannerById( Integer bannerType ) {
        List< Banner > list = bannerDao.findByTypeAndIsDelete( bannerType, 1 );
        for ( Banner banner : list ) {
            if ( banner.getPic() == null ) {
                continue;
            }
            banner.setPicUrl( banner.getPic().getFileMappingPath() );
            banner.setPic( null );
        }
        return new RestRecord( 200, list );
    }

    /**
     * 获取所有banner数据
     *
     * @return banner数据list
     */
    public RestRecord getAllBannersInfo() {
        List< Banner > list = bannerDao.findByIsDelete( 1 );
        for ( Banner banner : list ) {
            if ( banner.getPic() == null ) {
                continue;
            }
            banner.setPicUrl( banner.getPic().getFileMappingPath() );
            banner.setPic( null );
        }
        return new RestRecord( 200, list );
    }
}


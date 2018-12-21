package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.BannerDao;
import cn.com.bonc.sce.model.banner.Banner;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * banner
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@Service
public class BannerService {
    private BannerDao bannerDao;

    @Autowired
    public BannerService( BannerDao bannerDao ) {
        this.bannerDao = bannerDao;
    }

    /**
     * 添加banner
     *
     * @param banner 用户信息
     * @return 是否添加成功
     */
    public RestRecord insertBanner(Banner banner){
        return bannerDao.insertBanner(banner);
    }

    /**
     * 通过id删除banner
     *
     * @param bannerId  id
     * @return 删除是否成功
     */
    public RestRecord deleteBannerById(String bannerId){
        return bannerDao.deleteBannerById(bannerId);
    }

    /**
     * 更新banner
     *
     * @param banner banner信息
     * @return banner
     */
    public RestRecord updateBannerInfo( Banner banner ){
        return bannerDao.updateBannerInfo(banner);
    }

    /**
     * 修改url
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    public RestRecord updateBannerUrl(Banner banner){
        return bannerDao.updateBannerUrl(banner);
    }

    /**
     * 修改url
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    public RestRecord updateBannerAppId(Banner banner){
        return bannerDao.updateBannerAppId(banner);
    }

    /**
     * 获取banner数据
     *
     * @param bannerId bannerId
     * @return banner数据
     */
    public RestRecord getBannerById(String bannerId){
        return bannerDao.getBannerById(bannerId);
    }

    /**
     * 修改轮播次序
     *
     * @param list bannerId
     * @return 修改结果
     */
    public RestRecord updateBannerOrder( List<String> list ) {
        return bannerDao.updateBannerOrder( list );
    }

    /**
     * 获取所有banner数据
     *
     * @return banner数据list
     */
    public RestRecord getAllBannersInfo(){
        return bannerDao.getAllBannersInfo();
    }
}

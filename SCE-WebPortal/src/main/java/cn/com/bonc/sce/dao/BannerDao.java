package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Banner;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@FeignClient( "sce-data-access" )
public interface BannerDao {

    /**
     * 添加banner
     *
     * @param banner 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/banners", method = RequestMethod.POST )
    public RestRecord insertBanner( Banner banner );

    /**
     * 通过id删除banner
     *
     * @param bannerId  id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/banners/{bannerId}", method = RequestMethod.DELETE )
    public RestRecord deleteBannerById( @PathVariable( "bannerId" ) String bannerId );

    /**
     * 更新banner
     *
     * @param banner banner信息
     * @return banner
     */
    @RequestMapping( value = "/banners", method = RequestMethod.PUT )
    public RestRecord updateBannerInfo( Banner banner );

    /**
     * 修改url
     *
     * @param bannerId   bannerId
     * @param url 待修改的url
     * @return 跟新是否成功
     */
    @RequestMapping( value = "/banners/{bannerId}", method = RequestMethod.PATCH )
    public RestRecord updateBannerUrl( @PathVariable( "bannerId" ) String bannerId, @RequestParam( "url" ) String url );

    /**
     * 修改轮播次序
     *
     * @param list bannerId
     * @return 修改结果
     */
    @RequestMapping( value = "/banners/updateBannerOrder", method = RequestMethod.PUT )
    public RestRecord updateBannerOrder( @RequestParam( "bannerList" ) List<String> list );

    /**
     * 获取banner数据
     *
     * @param bannerId bannerId
     * @return banner数据
     */
    @RequestMapping( value = "/banners/{bannerId}", method = RequestMethod.GET )
    public RestRecord getBannerById( @PathVariable( "bannerId" ) String bannerId );

    /**
     * 获取所有banner数据
     *
     * @return banner数据list
     */
    @RequestMapping( value = "/banners", method = RequestMethod.GET )
    public RestRecord getAllBannersInfo();
}

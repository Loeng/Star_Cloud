//package cn.com.bonc.sce.api;
//
//import cn.com.bonc.sce.dao.BannerDao;
//import cn.com.bonc.sce.model.Banner;
//import cn.com.bonc.sce.rest.RestRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping( "/banners" )
//public class BannerApiController {
//    private BannerDao bannerDao;
//
//    @Autowired
//    public BannerApiController( BannerDao bannerDao ) {
//        this.bannerDao = bannerDao;
//    }
//
//    /**
//     * 添加banner
//     *
//     * @param banner 信息
//     * @return 是否添加成功
//     */
//    @PostMapping( "" )
//    @ResponseBody
//    public RestRecord insertBanner( Banner banner ) {
//        try{
//            return new RestRecord(bannerDao.insertBanner( banner ));
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//
//    /**
//     * 通过id删除banner
//     *
//     * @param bannerId  id
//     * @return 删除是否成功
//     */
//    @DeleteMapping( "/{bannerId}" )
//    @ResponseBody
//    public RestRecord deleteBannerById( @PathVariable( "bannerId" ) String bannerId ) {
//        try{
//            return new RestRecord(200,bannerDao.deleteBannerById( bannerId ));
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//
//    /**
//     * 更新banner
//     *
//     * @param banner banner信息
//     * @return banner
//     */
//    @PutMapping( "" )
//    @ResponseBody
//    public RestRecord updateBannerInfo( Banner banner ) {
//        try{
//            return new RestRecord(200,bannerDao.updateBannerInfo( banner ));
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//
//    /**
//     * 修改url
//     *
//     * @param bannerId   bannerId
//     * @param url 待修改的url
//     * @return 跟新是否成功
//     */
//    @PatchMapping( "/url/{bannerId}" )
//    @ResponseBody
//    public RestRecord updateBannerUrl(
//            @PathVariable( "bannerId" ) String bannerId,
//            @RequestParam( "url" ) String url ) {
//        try{
//            return new RestRecord(200,bannerDao.updateBannerUrl( bannerId, url ));
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//
//    /**
//     * 修改url
//     *
//     * @param bannerId   bannerId
//     * @param appId 待修改的appId
//     * @return 跟新是否成功
//     */
//    @PatchMapping( "/appId/{bannerId}" )
//    @ResponseBody
//    public RestRecord updateBannerAppId(
//            @PathVariable( "bannerId" ) String bannerId,
//            @RequestParam( "appId" ) String appId ) {
//        try{
//            return new RestRecord(200,bannerDao.updateBannerAppId( bannerId, appId ));
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//
//    /**
//     * 修改轮播次序
//     *
//     * @param list bannerId
//     * @return 修改结果
//     */
//    @PutMapping( "/updateBannerOrder" )
//    @ResponseBody
//    public RestRecord updateBannerOrder(List<String> list) {
//        try{
//            return new RestRecord(200,bannerDao.updateBannerOrder( list ));
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//
//    /**
//     * 获取banner数据
//     *
//     * @param bannerId bannerId
//     * @return banner数据
//     */
//    @GetMapping( "/123/{bannerId}" )
//    @ResponseBody
//    public RestRecord getBannerById( @PathVariable( "bannerId" ) String bannerId ) {
//        try{
//            return new RestRecord(200,bannerDao.getBannerById( bannerId));
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//
//    /**
//     * 获取所有banner数据
//     *
//     * @return banner数据list
//     */
//    @GetMapping( "/123" )
//    @ResponseBody
//    public RestRecord getAllBannersInfo() {
//        try{
//            return new RestRecord(200,bannerDao.getAllBannersInfo());
//        }catch ( Exception e ){
//            return new RestRecord(500,"", e);
//        }
//    }
//}
//

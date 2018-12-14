package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDao {

    public boolean insertBanner(Banner banner) {
        return true;
    }

    public Integer deleteBannerById(String bannerId) {
        return null;
    }

    public Integer updateBannerInfo(Banner banner) {
        return null;
    }

    public Integer updateBannerUrl(String bannerId,String url) {
        return null;
    }

    public Integer updateBannerOrder(List<String> list) {
        return null;
    }

    public Banner getBannerById( String bannerId) {
        return null;
    }

    public List<Banner> getAllBannersInfo() {
        return null;
    }
}

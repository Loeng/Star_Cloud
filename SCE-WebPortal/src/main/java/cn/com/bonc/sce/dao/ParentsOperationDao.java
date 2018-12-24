package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient( "sce-data-access" )
public interface ParentsOperationDao {
    /**
     * 获取安全验证信息
     *
     * @param phone 手机号
     * @return 验证码
     */
    //public RestRecord getSecurityVaildInfo( String phone);
    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/parentsOperation", method = RequestMethod.POST )
    public RestRecord insertParentsInfo( ParentsInfo parentsInfo );
}

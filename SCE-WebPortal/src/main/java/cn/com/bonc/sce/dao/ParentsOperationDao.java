package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    @RequestMapping( value = "/parents-operation", method = RequestMethod.POST )
    public RestRecord insertParentsInfo( ParentsInfo parentsInfo );

    /**
     * 用户注册
     *
     * @param info 注册信息
     * @return 添加结果
     */
    @RequestMapping( value = "/parents-operation/free", method = RequestMethod.POST )
    public RestRecord insertUsersInfo( ParentsInfo info );

    /**
     * 获取审核列表
     *
     * @return 结果
     */
    @RequestMapping( value = "/parents-operation/examine", method = RequestMethod.GET )
    public RestRecord getExamine(@RequestParam( "pageNum") Integer pageNum,
                                 @RequestParam( "pageSize" ) Integer pageSize);

    /**
     * 审核通过
     *
     * @param list 通过列表
     * @return 结果
     */
    @RequestMapping( value = "/parents-operation/examine", method = RequestMethod.POST )
    public RestRecord examine( List<String> list );
}

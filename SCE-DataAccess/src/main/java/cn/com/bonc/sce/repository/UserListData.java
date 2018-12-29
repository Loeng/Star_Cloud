package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author jc_D  用户列表假数据
 * @description 自己造的假数据，后期要删掉
 * @date 2018/12/19
 **/
// @Component
public class UserListData {
/*

    @Autowired
    private UserInfoRepository userInfoRepository;

    private Log log = LogFactory.get();
    */
/**
 * 自注册用户1
 * 学校2
 * 机构3
 * 厂商4
 * 代理商5
 * 审核用户6
 *//*


     */
/**
 * 自注册用户
 *
 * @param pageNum
 * @param pageSize
 *//*

    public static List ziZhuCe( int pageNum, int pageSize, int total ) {
        List list = new ArrayList();
        for ( int i = ( pageNum - 1 ) * pageSize; i < pageNum * pageSize; i++ ) {
            Map map = new HashMap();
            map.put( "USER_ACCOUNT", "xs12366" );
            map.put( "all", "xy2018042800" );
            map.put( "ACCOUNT_BALANCE", "100" );
            map.put( "ACCOUNT_CONSUMPTION", "0" );
            list.add( map );
        }
        return list;
    }

    public static List xueXiao( int pageNum, int pageSize, int total ) {
        List list = new ArrayList();
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "USER_ID");
        for ( int i = ( pageNum - 1 ) * pageSize; i < pageNum * pageSize; i++ ) {
            Map map = new HashMap();
            map.put( "SCHOOL_NAME", "xxx学校" );
            map.put( "USER_ACCOUNT", "xs12366" );
            //厂商名称
            map.put( "COMPANY_NAME", "xx教育局" );
            //账号
            map.put( "USER_ACCOUNT", "xs12363" + i );
            //状态
            map.put( "STATE", i % 2 );
            //关联代理商
            map.put( "ASSOCIATED_AGENT", "xxx教育局" );
            //代理商
            map.put( "AGENT", "xxx" );
            //允许登陆
            map.put( "ALLOW_LOGIN", 1 );
            list.add( map );
        }
        return list;
    }

    public static List jiGou( int pageNum, int pageSize, int total ) {
        List list = new ArrayList();
        for ( int i = ( pageNum - 1 ) * pageSize; i < pageNum * pageSize; i++ ) {
            Map map = new HashMap();
            //厂商名称
            map.put( "COMPANY_NAME", "xx教育局" );
            //账号
            map.put( "USER_ACCOUNT", "xs12363" + i );
            //状态
            map.put( "STATE", i % 2 );
            //关联代理商
            map.put( "ASSOCIATED_AGENT", "xxxxxx" );
            //允许登陆
            map.put( "ALLOW_LOGIN", 1 );
            list.add( map );
        }
        return list;
    }

    public static List changShang( int pageNum, int pageSize, int total ) {
        List list = new ArrayList();
        for ( int i = ( pageNum - 1 ) * pageSize; i < pageNum * pageSize; i++ ) {
            Map map = new HashMap();
            //厂商名称
            map.put( "COMPANY_NAME", "厂商" + i );
            //账号
            map.put( "USER_ACCOUNT", "xs12367" + i );
            //状态
            map.put( "STATE", i % 2 );
            //在运营软件数
            map.put( "SOFTWARE_COUNT", "小学" );
            //合同状态
            map.put( "CONTRACT_STATE", 1 );
            //允许登陆
            map.put( "ALLOW_LOGIN", 1 );
            list.add( map );
        }
        return list;
    }

    public static List daiLiShang( int pageNum, int pageSize, int total ) {
        List list = new ArrayList();
        for ( int i = ( pageNum - 1 ) * pageSize; i < pageNum * pageSize; i++ ) {
            Map map = new HashMap();
            //代理商id
            map.put( "USER_ACCOUNT", "xs12366" + i );
            //代理商名字
            map.put( "account_name", "李代理" );
            //状态
            map.put( "STATE", i % 2 );
            //代理学段
            map.put( "AGENT_PERIOD", "小学" );
            //所属地区
            map.put( "AREA", "福州市" );
            //允许登陆
            map.put( "ALLOW_LOGIN", 1 );
            list.add( map );
        }
        return list;
    }

    public static List shengHeYongHu( int pageNum, int pageSize, int total ) {
        List list = new ArrayList();
        for ( int i = ( pageNum - 1 ) * pageSize; i < pageNum * pageSize; i++ ) {
            Map map = new HashMap();
            map.put( "USER_ACCOUNT", "xs12366" + i );
            //性别
            map.put( "GENDER", ( i % 3 == 0 ? '男' : '女' ) );
            //用户类型
            map.put( "USER_TYPE", "教师；学生；老师；家长；代理商；学校用户；".split( "；" )[ ( i % 6 ) ] );
            //证件号
            map.put( "CERTIFICATE_NUMBER", "5110251993101672" + i );
            //联系方式
            map.put( "PHONE_NUMBER", "1380888123" + i );
            //申请用户
            map.put( "APPLICATION_USER", "xxx1" + i % 3 );
            list.add( map );
        }
        return list;
    }


    // 查询 学校 -》 教师信息
    public Page<Map<String,Object>> teacher(int pageNum, int pageSize, Map<String,Object> condition){
        Page info;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "USER_ID");
        String account= "";
        String name = "";
        String organization_name = "";
        if (null != condition){
            if (StringUtils.isNotBlank(condition.get("account").toString())){
                account = condition.get("account").toString();
            }
            if (StringUtils.isNotBlank(condition.get("name").toString())){
                name = condition.get("name").toString();
            }
            if (StringUtils.isNotBlank(condition.get("organization_name").toString())){
                organization_name = condition.get("organization_name").toString();
            }
        }
        info = userInfoRepository.findTeacherByCondition(name,account,organization_name,pageable);
        log.info("一共查询到[{}]条符合条件的信息",info.getTotalElements());
        return info;

    }

    // 查询 学校 -》 学生信息
    public Page<Map<String,Object>> student(int pageNum, int pageSize, Map<String,Object> condition){
        Page info;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "USER_ID");
        String account= "";
        String name = "";
        String organization_name = "";
        if (null != condition){
            if (StringUtils.isNotBlank(condition.get("account").toString())){
                account = condition.get("account").toString();
            }
            if (StringUtils.isNotBlank(condition.get("name").toString())){
                name = condition.get("name").toString();
            }
            if (StringUtils.isNotBlank(condition.get("organization_name").toString())){
                organization_name = condition.get("organization_name").toString();
            }
        }
        info =  userInfoRepository.findSchoolByCondition(name,account,organization_name,pageable);
        log.info("一共查询到[{}]条符合条件的信息",info.getTotalElements());
        return info;
    }

    // 查询 学校 -》 家长信息

    public Page<Map<String,Object>> family(int pageNum, int pageSize, Map<String,Object> condition){
        Page info;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "USER_ID");
        String account= "";
        String name = "";
        if (null != condition){
            if (StringUtils.isNotBlank(condition.get("account").toString())){
                account = condition.get("account").toString();
            }
            if (StringUtils.isNotBlank(condition.get("name").toString())){
                name = condition.get("name").toString();
            }
        }
        info =  userInfoRepository.findFamilyByCondition(name,account,pageable);
        log.info("一共查询到[{}]条符合条件的信息",info.getTotalElements());
        return info;
    }
*/



}

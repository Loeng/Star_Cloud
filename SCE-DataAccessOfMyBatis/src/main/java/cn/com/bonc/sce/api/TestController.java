package cn.com.bonc.sce.api;

import cn.com.bonc.sce.mapper.LiveMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2019/2/12 16:19
 * @Description:
 */
@RestController
@RequestMapping( "/test" )
public class TestController {
    @Autowired
    LiveMapper liveMapper;

    @RequestMapping( value = "/get", method = RequestMethod.GET )
    public List< Map > selectAllInfo() {
        return liveMapper.getAllInfo();
    }

    @RequestMapping( value = "/getPage/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public Map getPageInfo( @PathVariable( "pageNum" ) Integer pageNum,
                                    @PathVariable( "pageSize" ) Integer pageSize ) {
        Page< Object > page = PageHelper.startPage( pageNum, pageSize );
        List< Map > info = liveMapper.getAllInfo();
        Map  map=new HashMap( 3 );
        map.put( "data",info );
        map.put( "totalPage",page.getPages() );
        map.put( "totalCount",page.getTotal() );
        return map;
    }

}

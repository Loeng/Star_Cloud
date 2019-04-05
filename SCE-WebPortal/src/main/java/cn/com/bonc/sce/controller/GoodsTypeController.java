package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GoodsTypeController
 * @Description 商品类型查询   这里单独写出来,方便调用   但调的方法还是优惠码类的商品类型查询
 * @Author YQ
 * @Date 2019/4/5 11:16
 * @Version 1.0
 */
@Slf4j
@RestController
@Api( value = "商品类型查询", tags = "商品类型查询"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/goodstype" )
public class GoodsTypeController {

    @Autowired
    private CouponService couponService;


    @ApiOperation( value = "商品类型查询", notes = "商品类型查询", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/query-goodsType")
    @ResponseBody
    public RestRecord queryGoodsType(){
        return couponService.queryGoodsType();
    }

}

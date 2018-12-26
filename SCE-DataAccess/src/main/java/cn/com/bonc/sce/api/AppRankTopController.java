package cn.com.bonc.sce.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.entity.News;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppTopRankService;
import lombok.extern.slf4j.Slf4j;
/**
 * yanmin
 * @author Administrator
 *
 */
@Slf4j
@RestController
@RequestMapping( "/rank-app" )
public class AppRankTopController {
	  @Autowired
	  private AppTopRankService appTopRankService;
	  
	  @GetMapping("/top-rank-app")
	  @ResponseBody
	  public RestRecord insertNews( @RequestParam( value = "topSize", required = false, defaultValue = "10" ) Integer topSize ) {
	        try {
	            return appTopRankService.getAppTopRank(topSize);
	        } catch ( Exception e ) {
	            log.error( e.getMessage(), e );
	            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
	        }
	    }
}

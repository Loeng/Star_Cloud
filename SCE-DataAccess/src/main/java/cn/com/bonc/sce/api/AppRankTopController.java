package cn.com.bonc.sce.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	  
	  @GetMapping("/top-rank-app/{topSize}/{userId}")
	  @ResponseBody
	  public RestRecord insertNews( @PathVariable( value = "topSize" ) Integer topSize,
									@PathVariable( value = "userId" ) String userId ) {
	        try {
	            return appTopRankService.getAppTopRank( topSize, userId );
	        } catch ( Exception e ) {
	            log.error( e.getMessage(), e );
	            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
	        }
	    }
}

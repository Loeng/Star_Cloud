package cn.com.bonc.sce.controller;


import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/app-portal")
public class AppMarketController {

    private AppMarketService appMarketService;

    @Autowired
    public AppMarketController(AppMarketService appMarketService){
        this.appMarketService = appMarketService;
    }

    @GetMapping("/appCount")
    public RestRecord appCount(){
        return appMarketService.appCount();
    }

    @GetMapping("/userToDo")
    public RestRecord userToDo(){
        return appMarketService.userToDo();
    }
}

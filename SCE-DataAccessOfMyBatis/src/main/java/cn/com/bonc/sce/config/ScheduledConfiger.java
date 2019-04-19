package cn.com.bonc.sce.config;

import cn.com.bonc.sce.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @ClassName ScheduledConfiger
 * @Description 订单-- 定时扫描(每天24：00)  更新已支付  已取消的订单为已关闭
 * @Author YQ
 * @Date 2019/4/18 18:08
 * @Version 1.0
 */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduledConfiger {

    @Value("${TimeWork.isOpen}")
    private boolean isOpen;

    @Autowired
    private OrderService orderService;


    @Scheduled(cron = "0 0 24 * * ?")  // 每天24：00执行一次
    public void updateOrderStatus(){
        if (!isOpen){
            log.info("订单定时更新任务--------------已关闭");
            return;
        }
        log.info("订单定时更新任务--------------已开始"+new Date().getTime());

        orderService.updateOrderStatus();

        log.info("订单定时更新任务--------------已结束"+new Date().getTime());
    }
}

package cn.com.bonc.sce.mapper;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface AppMarketMapper {

    Map selectAppCount();

}

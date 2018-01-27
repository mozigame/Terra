package com.babel.terra.feign;

import com.babel.terra.TerraServer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(TerraServer.SERVER_NAME)
public interface TerraFeign {
    @RequestMapping(TerraServer.USER_GET_IP)
    public String getIp();
}

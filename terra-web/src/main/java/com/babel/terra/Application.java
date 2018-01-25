package com.babel.terra;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.babel.terra.config.ApplicationProperties;

@Configuration
@ComponentScan({"com.babel.terra"})
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableScheduling
@EnableAutoConfiguration
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableDiscoveryClient
//@PropertySource(value = {"classpath:codis-crius.properties", "classpath:crius-db.properties", "classpath:crius-kafka.properties",
//        "classpath:crius-mongo.properties", "classpath:dubbo.properties", "classpath:crius-config.properties"})
//@EnableDiscoveryClient //用于启动服务发现功能
//@EnableEurekaClient
@ImportResource(value = {"classpath:spring/spring-applications.xml"})
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.setProperty("user.timezone", "America/New_York");
        SpringApplication.run(Application.class, args);



    }
}

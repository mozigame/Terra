package com.babel.terra.rest;

import com.babel.terra.po.UserPO;
import com.babel.terra.service.UserService;
import com.babel.terra.vo.PageVo;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class UserResource {


    @Resource
    private UserService userService;

    @RequestMapping("/get/ip")
    public String getIp(HttpServletRequest request) {
        String ip = request.getRemoteHost();
        System.out.println(""+discoveryClient.getServices());
        return ip;
    }

    @PostMapping(value = "/add")
    public boolean save(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "address", required = false) String address
    ) {
        UserPO userPO = new UserPO();
        userPO.setId(id);
        userPO.setName(name);
        userPO.setAddress(address);
        userPO.setAge(age);
        userPO.setCreateTime(new Date().getTime());
        return userService.saveUser(userPO);
    }

    @PostMapping(value = "/update")
    public boolean update(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "address", required = false) String address
    ) {
        UserPO userPO = new UserPO();
        userPO.setId(id);
        userPO.setName(name);
        userPO.setAddress(address);
        userPO.setAge(age);
        return userService.updateUser(userPO);
    }
    
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping(value = "/get")
    public UserPO get(
            @RequestParam(name = "id", required = false) String id
    ) {
        return userService.get(id);
    }


    @GetMapping(value = "/query_list")
    public PageVo<UserPO> query_list(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "rows", required = false, defaultValue = "15") Integer rows
    ) {
        UserPO po = new UserPO();
        po.setId(id);
        po.setAddress(address);
        po.setName(name);
        po.setAge(age);
        return userService.queryList(po, page, rows);
    }


}

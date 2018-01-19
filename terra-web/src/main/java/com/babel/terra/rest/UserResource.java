package com.babel.terra.rest;

import com.babel.terra.po.UserPO;
import com.babel.terra.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserResource {


    @Resource
    private UserService userService;

    @RequestMapping("/get/ip")
    public String getIp(HttpServletRequest request) {
        String ip = request.getRemoteHost();
        return ip;
    }

    @PostMapping(value = "/add")
    private boolean save(
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
        return userService.saveUser(userPO);
    }
}

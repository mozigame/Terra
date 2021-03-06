package com.babel.terra.rest;

import com.babel.terra.po.UserPO;
import com.babel.terra.service.UserService;
import com.babel.terra.storage.hb.UserHbaseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2018/1/30
 * Time: 2:18
 */

@RestController
@RequestMapping("/")
public class KafkaUserResource {

    @Resource
    private UserService userService;

    @PostMapping(value = "/api/kf/user/add")
    public String save(
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
        return userService.sendMsg(userPO);
    }

//    @GetMapping(value = "/api/user/kf/get")
//    public UserPO get(
//            @RequestParam(name = "id", required = false) String id
//    ) {
//        return userHbaseService.get(id);
//    }
}

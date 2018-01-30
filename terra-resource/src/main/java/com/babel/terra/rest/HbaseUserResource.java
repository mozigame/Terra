package com.babel.terra.rest;

import com.babel.terra.po.UserPO;
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
public class HbaseUserResource {

    @Resource
    private UserHbaseService userHbaseService;

    @PostMapping(value = "/api/hb/user/add")
    public boolean save(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "address", required = false) String address
    ) {

        System.out.println(System.getenv("HADOOP_HOME") );
        UserPO userPO = new UserPO();
        userPO.setId(id);
        userPO.setName(name);
        userPO.setAddress(address);
        userPO.setAge(age);
        userPO.setCreateTime(new Date().getTime());
        return userHbaseService.add(userPO);
    }

    @GetMapping(value = "/api/user/hb/get")
    public UserPO get(
            @RequestParam(name = "id", required = false) String id
    ) {
        return userHbaseService.get(id);
    }
}

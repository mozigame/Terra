package com.babel.terra.service;

import com.babel.terra.es.UserEsService;
import com.babel.terra.po.UserPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserEsService userEsService;


    public boolean saveUser(UserPO userPO) {
        userEsService.save(userPO);
        return true;
    }

}

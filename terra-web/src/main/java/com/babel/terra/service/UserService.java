package com.babel.terra.service;

import com.babel.terra.es.UserEsService;
import com.babel.terra.po.UserPO;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserEsService userEsService;

    public boolean saveUser(UserPO userPO) {
        userEsService.save(userPO);
        return true;
    }

    public UserPO get(String id) {
        Optional<UserPO> optional = userEsService.findById(id);
        return optional.orElse(null);
    }

}

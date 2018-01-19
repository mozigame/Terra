package com.babel.terra.es;


import com.babel.terra.po.UserPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

//@Repository
public interface UserEsService extends ElasticsearchRepository<UserPO, String> {

}

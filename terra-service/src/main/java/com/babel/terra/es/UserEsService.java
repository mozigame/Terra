package com.babel.terra.es;


import com.babel.terra.po.UserPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

//@Repository
public interface UserEsService extends ElasticsearchRepository<UserPO, String> {


}

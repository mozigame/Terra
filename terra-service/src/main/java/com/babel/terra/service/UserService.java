package com.babel.terra.service;

import com.alibaba.fastjson.JSON;
import com.babel.terra.es.UserEsService;
import com.babel.terra.po.UserPO;
import com.babel.terra.vo.PageVo;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserEsService userEsService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public boolean saveUser(UserPO userPO) {
        userEsService.save(userPO);
//        IndexQuery indexQuery = new IndexQuery();
//        indexQuery.setId(userPO.getId());
//        indexQuery.setIndexName();
//        elasticsearchTemplate.bulkIndex();
        return true;
    }

    public boolean updateUser(UserPO userPO) {
        userEsService.save(userPO);
        return true;
    }

    public UserPO get(String id) {
        Optional<UserPO> optional = userEsService.findById(id);
        return optional.orElse(null);
    }

    public PageVo<UserPO> queryList(UserPO userPO, int page, int rows) {
        System.out.println(JSON.toJSONString(userPO));
        Criteria criteria = new Criteria();
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        if (StringUtils.isNotBlank(userPO.getName())) {
            criteria.and(new Criteria("name").startsWith(userPO.getName()));
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("name", userPO.getName()));
        }
        if (StringUtils.isNotBlank(userPO.getAddress())) {
            criteria.and(new Criteria("address").is(userPO.getAddress()));
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("address", userPO.getAddress()));
        }
        if (StringUtils.isNotBlank(userPO.getId())) {
            criteria.and(new Criteria("id").is(userPO.getId()));
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("id", userPO.getId()));
        }
        if (userPO.getAge() != null) {
            criteria.and(new Criteria("age").is(userPO.getAge()));
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("age", userPO.getAge()));
        }
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(page, rows, sort);
        CriteriaQuery query = new CriteriaQuery(criteria);
        query.setPageable(pageable);
//        query.addSort(new Sort(Sort.Direction.ASC, "id"));

        queryBuilder.withPageable(pageable);

        SearchQuery searchQuery = queryBuilder.build();

        Page<UserPO> pageResult = elasticsearchTemplate.queryForPage(query, UserPO.class);
        Page<UserPO> pageResult_1 = elasticsearchTemplate.queryForPage(searchQuery, UserPO.class);

        System.out.println(JSON.toJSONString(pageResult.getContent()));
        System.out.println(JSON.toJSONString(pageResult_1.getContent()));

        PageVo<UserPO> pageVo = new PageVo<>();
        pageVo.setSize(rows);
        pageVo.setPage(page);
        pageVo.setTotal(pageResult.getTotalElements());
        pageVo.setData(pageResult.getContent());
        return pageVo;
    }

}

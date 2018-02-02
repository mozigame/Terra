package com.babel.terra.service.impl;

import com.alibaba.fastjson.JSON;
import com.babel.common.core.page.PageVO;
import com.babel.terra.es.CashFlowBaseEsService;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.service.CashFlowBaseService;
import com.babel.terra.vo.CashFlowBaseVO;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 2:55
 */
@Service
public class CashFlowBaseServiceImpl implements CashFlowBaseService {

    private static final Logger log = LoggerFactory.getLogger(CashFlowBaseServiceImpl.class);

    @Resource
    private CashFlowBaseEsService cashFlowBaseEsService;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public boolean save(CashFlowBase cashFlowBase) {
        try {
            cashFlowBaseEsService.save(cashFlowBase);
            return true;
        } catch (Exception e) {
            log.error("--> cashFlowBase es save error :: {}", JSON.toJSONString(cashFlowBase), e);
            return false;
        }
    }

    @Override
    public CashFlowBase get(String cashFlowId) {
        Optional<CashFlowBase> base = cashFlowBaseEsService.findById(cashFlowId);
        return base.orElse(null);
    }

    @Override
    public void queryList(CashFlowBaseVO base, PageVO<CashFlowBase> pageVo) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        if (StringUtils.isNotBlank(base.getId())) {
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("id", base.getId()));
        }
        if (base.getMemberId() != null) {
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("memberId", base.getMemberId()));
        }
        if (StringUtils.isNotBlank(base.getMemberName())) {
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("memberName", base.getMemberName()));
        }
        if (base.getAgentId() != null) {
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("agentId", base.getAgentId()));
        }
        if (StringUtils.isNotBlank(base.getAgentName())) {
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("agentName", base.getAgentName()));
        }
        if (base.getTradeType() != null) {
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("tradeType", base.getTradeType()));
        }
        if (base.getTradeType() != null) {
            queryBuilder.withQuery(QueryBuilders.matchPhrasePrefixQuery("tradeType", base.getTradeType()));
        }
        if (base.getStartTime() != null) {
            RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("createTime");
            rangeQueryBuilder.gte(base.getStartTime());
            if (base.getEndTime() != null) {
                rangeQueryBuilder.lte(base.getEndTime());
            }
            queryBuilder.withFilter(rangeQueryBuilder);
        } else {
            RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("createTime");
            if (base.getEndTime() != null) {
                rangeQueryBuilder.lte(base.getEndTime());
            }
            queryBuilder.withFilter(rangeQueryBuilder);
        }
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("createTime");

        queryBuilder.withQuery(rangeQueryBuilder);
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(pageVo.getCurrentPage() - 1, pageVo.getPageSize(), sort);
        queryBuilder.withPageable(pageable);
        SearchQuery searchQuery = queryBuilder.build();
        Page<CashFlowBase> pageResult = elasticsearchTemplate.queryForPage(searchQuery, CashFlowBase.class);
        pageVo.setTotal((int) pageResult.getTotalElements());
        pageVo.setRows(pageResult.getContent());
    }
}

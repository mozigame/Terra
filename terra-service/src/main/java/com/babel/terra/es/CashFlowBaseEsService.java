package com.babel.terra.es;

import com.babel.terra.po.CashFlowBase;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 2:54
 */
public interface CashFlowBaseEsService extends ElasticsearchRepository<CashFlowBase, String> {
}

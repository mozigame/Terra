package com.babel.terra.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babel.terra.enums.KafkaConf;
import com.babel.terra.po.CashFlow;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.service.CashFlowBaseService;
import com.babel.terra.service.impl.CashFlowBaseServiceImpl;
import com.babel.terra.storage.hb.CashFlowHbService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2018/1/31
 * Time: 18:38
 */
@Service
public class CashFlowKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(CashFlowKafkaListener.class);

    @Resource
    private CashFlowBaseService cashFlowBaseService;
    @Resource
    private CashFlowHbService cashFlowHbService;

    @KafkaListener(topics = KafkaConf.ORDER_FLOW_TOPIC)
    public void cashFlowListener(ConsumerRecord<?, ?> record) {
        if (record.value() != null) {
            try {
                logger.info("--> kafka consumer : " + record.value());
                CashFlow cashFlow = JSONObject.parseObject(record.value().toString(), CashFlow.class);
                CashFlowBase cashFlowBase = CashFlowBase.parse(cashFlow);
                boolean flag = cashFlowBaseService.save(cashFlowBase);
                logger.info("--> es save cashFlowBase :{} , result :{}", cashFlow.getCashFlowId(), flag);
                cashFlowHbService.save(cashFlow);
            } catch (Exception e) {
                logger.error("--> kafka consumer error ::{}", JSON.toJSONString(record), e);
            }
        }
    }
}

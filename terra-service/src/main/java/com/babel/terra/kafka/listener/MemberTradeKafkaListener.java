package com.babel.terra.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babel.common.kafka.KafkaConstants;
import com.babel.terra.enums.KafkaConf;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.po.MemberTrade;
import com.babel.terra.service.impl.CashFlowBaseServiceImpl;
import com.babel.terra.storage.hb.MemberTradeHbService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 21:36
 */
@Component
public class MemberTradeKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(MemberTradeKafkaListener.class);

    @Resource
    private CashFlowBaseServiceImpl cashFlowBaseService;
    @Resource
    private MemberTradeHbService memberTradeHbService;

    @KafkaListener(topics = KafkaConstants.MEMBER_TRADE_TOPIC)
    public void memberTradeListener(ConsumerRecord<?, ?> record) {
        if (record.value() != null) {
            try {
                logger.info("--> kafka consumer :: " + record.value());
                MemberTrade memberTrade = JSONObject.parseObject(record.value().toString(), MemberTrade.class);
                CashFlowBase cashFlowBase = CashFlowBase.parse(memberTrade);
                boolean flag = cashFlowBaseService.save(cashFlowBase);
                logger.info("--> kafka save cashFlowBase result :{}", flag);
                memberTradeHbService.save(memberTrade);
            } catch (Exception e) {
                logger.error("--> kafka consumer error ::{}", JSON.toJSONString(record), e);
            }
        }
    }
}

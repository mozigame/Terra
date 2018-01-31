package com.babel.terra.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babel.terra.enums.KafkaConf;
import com.babel.terra.po.UserPO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * User: joey
 * Date: 2018/1/31
 * Time: 18:38
 */
@Service
public class UserKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(UserKafkaListener.class);

    @KafkaListener(topics = KafkaConf.USER_TOPIC, groupId = KafkaConf.CAPITAL_GROUP)
    public void eGameListen(ConsumerRecord<?, ?> record) {
        if (record.value() != null) {
            logger.info("--> kafka consumer : " + record.value());
            UserPO userPO = JSONObject.parseObject(record.value().toString(), UserPO.class);
            System.out.println(JSON.toJSONString(userPO));
        }
    }
}

package com.babel.terra.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:32
 * kafka 离线数据的type类型
 */
public class KafkaConf {


    public static final String TOPICS[] = new String[]{"plutus", "hera"};

    public static final String DATA = "Data";

    public static final String DATA_TYPE = "DataType";

    public static final String CAPITAL_GROUP = "capital_group";
    /**
     * kafka 游戏中的数据
     */
    public static final String RECORD = "Record";

    public static final String USER_TOPIC = "user_topic";
    /**
     * 会员金流，出入款
     */
    public static final String MEMBER_TRADE_TOPIC = "ARES_MEMBER_TRADE";
    /**
     * 订单金流
     */
    public static final String ORDER_FLOW_TOPIC = "ARES_ORDER_FLOW";


}

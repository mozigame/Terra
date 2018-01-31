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


    public static final String TOPICS [] = new String[]{"plutus", "hera"};

    public static final String DATA = "Data";

    public static final String DATA_TYPE="DataType";

    public static final String CAPITAL_GROUP = "capital_group";
    /**
     * kafka 游戏中的数据
     */
    public static final String RECORD = "Record";

    public static final String USER_TOPIC="user_topic";
   

    /**
     * kafka消费的数据类型
     */
    public enum DataType {

        ;    

        private static Map<Integer, DataType> map = new HashMap<>();

        static {
            for (DataType kafkaType : DataType.values()) {
                map.put(kafkaType.type(), kafkaType);
            }
        }

        private Integer type;

        private String typeName;

        DataType(Integer type,String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        public Integer type() {
            return type;
        }
        public String typeName() {
            return typeName;
        }

        public static DataType parse(Integer type) {
            return map.get(type);
        }
    }
}

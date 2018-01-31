package com.babel.terra.storage.hb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babel.terra.po.UserPO;
import com.babel.terra.util.HbaseFindBuilder;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2018/1/30
 * Time: 1:43
 */
@Service
public class UserHbaseService {

    private static final Logger log = LoggerFactory.getLogger(UserHbaseService.class);
    @Resource
    private HbaseTemplate hbaseTemplate;

    public boolean add(UserPO po) {
        hbaseTemplate.put("user_vo", po.getId(), "message", "orderDetail", Bytes.toBytes(JSON.toJSONString(po)));
        return true;
    }

    public UserPO get(String id) {

        UserPO userPO = hbaseTemplate.get("user_vo", id, "message", new RowMapper<UserPO>() {
            @Override
            public UserPO mapRow(Result result, int rowNum) throws Exception {
                String orderDetail = Bytes.toString(result.getValue(Bytes.toBytes("message"), Bytes.toBytes("orderDetail")));
                UserPO userPO = JSONObject.parseObject(orderDetail, UserPO.class);
                return userPO;
            }
        });
//        UserPO userPO = (UserPO) hbaseTemplate.get("user_vo", id, "message",
//                (result, rowNum) -> new HbaseFindBuilder<>("message", result, UserPO.class).build("orderDetail").fetch());
        log.info(JSON.toJSONString(userPO));
        return userPO;
    }
}

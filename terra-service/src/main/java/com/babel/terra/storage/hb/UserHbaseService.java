package com.babel.terra.storage.hb;

import com.alibaba.fastjson.JSON;
import com.babel.terra.po.UserPO;
import com.babel.terra.util.HbaseFindBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2018/1/30
 * Time: 1:43
 */
@Service
public class UserHbaseService {
    @Resource
    private HbaseTemplate hbaseTemplate;


    public boolean add(UserPO po) {
        hbaseTemplate.put("user_vo", po.getId(), "message", "orderDetail", Bytes.toBytes(JSON.toJSONString(po)));
        return true;
    }

    public UserPO get(String id) {
        return (UserPO) hbaseTemplate.get("user_vo", id, "message",
                (result, rowNum) -> new HbaseFindBuilder<>("message", result, UserPO.class).build("orderDetail").fetch());
    }
}

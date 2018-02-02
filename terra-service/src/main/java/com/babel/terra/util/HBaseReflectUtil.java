package com.babel.terra.util;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 11:39
 */
public class HBaseReflectUtil {

    private static final Logger logger = LoggerFactory.getLogger(HBaseReflectUtil.class);

    /**
     * 获取多行数据
     *
     * @param rows
     * @return
     * @throws Exception
     */
    public static <T> Result[] getRows(Table table, List<T> rows) {
        List<Get> gets = null;
        Result[] results = null;
        try {
            if (table != null) {
                gets = new ArrayList<Get>();
                for (T row : rows) {
                    if (row != null) {
                        gets.add(new Get(Bytes.toBytes(String.valueOf(row))));
                    } else {
                        throw new RuntimeException("hbase have no data");
                    }
                }
            }
            if (gets != null && gets.size() > 0) {
                results = table.get(gets);
            }
        } catch (IOException e) {
            logger.error("getRows failure !", e);
        }
        return results;
    }

}

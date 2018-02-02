package com.babel.terra.storage.hb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babel.terra.common.HbaseTableInfo;
import com.babel.terra.po.CashFlow;
import com.babel.terra.po.MemberTrade;
import com.babel.terra.util.ThreadPoolFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 21:38
 */
@Service
public class MemberTradeHbService {

    private static final Logger log = LoggerFactory.getLogger(MemberTradeHbService.class);
    private static ExecutorService THREAD_POOL = ThreadPoolFactory.THREAD_POOL;
    @Resource
    private HbaseTemplate hbaseTemplate;

    public void save(MemberTrade memberTrade) {
        THREAD_POOL.execute(() -> {
            try {
                hbaseTemplate.put(HbaseTableInfo.MEMBER_TRADE_TB_NAME, memberTrade.getOrderNo(), HbaseTableInfo.MEMBER_TRADE_FAMILY_NAME, HbaseTableInfo.GATHER_DETAIL, Bytes.toBytes(JSON.toJSONString(memberTrade)));
                log.info("--> hb put memberTrade success :{}", memberTrade.getOrderNo());
            } catch (Exception e) {
                log.error("--> hb put member_trade error :: {}", JSON.toJSONString(memberTrade), e);
            }
        });

    }

    public MemberTrade get(String orderNo) {
        try {
            return hbaseTemplate.get(HbaseTableInfo.MEMBER_TRADE_TB_NAME, orderNo, HbaseTableInfo.MEMBER_TRADE_FAMILY_NAME, (result, rowNum) -> {
                String gatherDetail = Bytes.toString(result.getValue(Bytes.toBytes(HbaseTableInfo.CASH_FLOW_FAMILY_NAME), Bytes.toBytes(HbaseTableInfo.GATHER_DETAIL)));
                log.info("--> HBase get member_trade : {}, result:{}", orderNo, gatherDetail);
                if (gatherDetail != null) {
                    return JSONObject.parseObject(gatherDetail, MemberTrade.class);
                }
                return null;
            });
        } catch (Exception e) {
            log.error("--> hb get member_trade error :: {}", orderNo, e);
        }
        return null;
    }

    public List<MemberTrade> queryList(List<String> orderNos) {
        hbaseTemplate.execute(HbaseTableInfo.MEMBER_TRADE_TB_NAME, action -> {
            List<Get> gets = new ArrayList<>();
            orderNos.forEach(rk -> {
                Get get = new Get(Bytes.toBytes(rk));
                get.addColumn(Bytes.toBytes(HbaseTableInfo.MEMBER_TRADE_FAMILY_NAME), Bytes.toBytes(HbaseTableInfo.GATHER_DETAIL));
                gets.add(get);
            });
            Result[] cashFlows = action.get(gets);
            for (Result r : cashFlows) {
                List<Cell> cells = r.getColumnCells(Bytes.toBytes(HbaseTableInfo.MEMBER_TRADE_FAMILY_NAME), Bytes.toBytes(HbaseTableInfo.GATHER_DETAIL));
                if (cells != null && cells.size() > 0) {
                    cells.forEach(cell -> {
                        log.info("--> batch get member_trade : {}", Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()) + " Value: " + Bytes.toString(CellUtil.cloneValue(cell)));
                    });
                }
            }
            return null;
        });
        return null;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000_0000; i++) {
            THREAD_POOL.execute(() -> {
                log.info("--> aaaaaaaaa");
            });
        }

    }
}

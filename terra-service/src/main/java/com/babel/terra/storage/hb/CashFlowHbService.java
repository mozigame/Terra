package com.babel.terra.storage.hb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babel.terra.common.HbaseTableInfo;
import com.babel.terra.po.CashFlow;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.util.ThreadPoolFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 10:52
 */
@Service
public class CashFlowHbService {

    private static final Logger log = LoggerFactory.getLogger(CashFlowHbService.class);

    private static ExecutorService THREAD_POOL = ThreadPoolFactory.THREAD_POOL;

    @Resource
    private HbaseTemplate hbaseTemplate;

    public void save(CashFlow cashFlow) {
        THREAD_POOL.execute(() -> {
            try {
                hbaseTemplate.put(HbaseTableInfo.CASH_FLOW_TB_NAME, cashFlow.getCashFlowId(), HbaseTableInfo.CASH_FLOW_FAMILY_NAME, HbaseTableInfo.GATHER_DETAIL, Bytes.toBytes(JSON.toJSONString(cashFlow)));
                log.info("--> hb put cash flow success : {}", cashFlow.getCashFlowId());
            } catch (Exception e) {
                log.error("--> hb put cash flow error :: {}", JSON.toJSONString(cashFlow), e);
            }
        });
    }

    public CashFlow get(String cashFlowId) {
        try {
            return hbaseTemplate.get(HbaseTableInfo.CASH_FLOW_TB_NAME, cashFlowId, HbaseTableInfo.CASH_FLOW_FAMILY_NAME, (result, rowNum) -> {
                String gatherDetail = Bytes.toString(result.getValue(Bytes.toBytes(HbaseTableInfo.CASH_FLOW_FAMILY_NAME), Bytes.toBytes(HbaseTableInfo.GATHER_DETAIL)));
                log.info("--> HBase get cashflow : {}, result:{}", cashFlowId, gatherDetail);
                if (gatherDetail != null) {
                    return JSONObject.parseObject(gatherDetail, CashFlow.class);
                }
                return null;
            });
        } catch (Exception e) {
            log.error("--> hb get cash flow error :: {}", cashFlowId, e);
        }
        return null;
    }

    public List<CashFlow> queryList(List<String> cashFlowIds) {
        try {
            return hbaseTemplate.execute(HbaseTableInfo.CASH_FLOW_TB_NAME, action -> {
                List<Get> gets = new ArrayList<>();
                cashFlowIds.forEach(rk -> {
                    Get get = new Get(Bytes.toBytes(rk));
                    get.addColumn(Bytes.toBytes(HbaseTableInfo.CASH_FLOW_FAMILY_NAME), Bytes.toBytes(HbaseTableInfo.GATHER_DETAIL));
                    gets.add(get);
                });
                Result[] cashFlows = action.get(gets);
                List<CashFlow> cashFlowList = new ArrayList<>();
                for (Result r : cashFlows) {
                    List<Cell> cells = r.getColumnCells(Bytes.toBytes(HbaseTableInfo.CASH_FLOW_FAMILY_NAME), Bytes.toBytes(HbaseTableInfo.GATHER_DETAIL));
                    if (cells != null && cells.size() > 0) {
                        cells.forEach(cell -> {
                            byte[] value = CellUtil.cloneValue(cell);
                            if (value.length > 0) {
                                CashFlow cf = JSONObject.parseObject(Bytes.toString(value), CashFlow.class);
                                cashFlowList.add(cf);
                            }
                            log.info("--> batch get cash_flow : {}", Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()) + " Value: " + Bytes.toString(CellUtil.cloneValue(cell)));
                        });
                    }
                }
                return cashFlowList;
            });
        } catch (Exception e) {
            log.error("--> bat get HBase cashFlow error :" + cashFlowIds, e);
        }
        return null;
    }
}

package com.babel.terra.rest;

import com.babel.terra.po.CashFlow;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.service.CashFlowBaseService;
import com.babel.terra.service.impl.CashFlowBaseServiceImpl;
import com.babel.terra.storage.hb.CashFlowHbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 3:15
 */
@RestController
@RequestMapping("/")
public class CashFlowResource {

    @Resource
    private CashFlowBaseService cashFlowBaseService;
    @Resource
    private CashFlowHbService cashFlowHbService;
    /**
     * 获取金流基础信息
     * es
     * @param id
     * @return
     */
    @GetMapping(value = "/api/es/cash_flow/get")
    public CashFlowBase get(
            @RequestParam(name = "id") String id
    ) {
        return cashFlowBaseService.get(id);
    }

    /**
     * 根据id获取金流信息
     * HBase
     * @param id
     * @return
     */
    @GetMapping(value = "/api/hb/cash_flow/get")
    public CashFlow getHb(
            @RequestParam(name = "id") String id
    ) {
        return cashFlowHbService.get(id);
    }

    @GetMapping(value = "/api/hb/cash_flow/batch_get")
    public List<CashFlow> batchGetHb(
            @RequestParam(name = "ids") List<String> ids
    ) {
        return cashFlowHbService.queryList(ids);
    }
}

package com.babel.terra.rest;

import com.babel.terra.po.CashFlow;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.po.MemberTrade;
import com.babel.terra.service.CashFlowBaseService;
import com.babel.terra.storage.hb.CashFlowHbService;
import com.babel.terra.storage.hb.MemberTradeHbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Member;
import java.util.List;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 3:15
 */
@RestController
@RequestMapping("/")
public class MemberTradeResource {

    @Resource
    private MemberTradeHbService memberTradeHbService;

    /**
     * 根据id获取金流信息
     * HBase
     * @param id
     * @return
     */
    @GetMapping(value = "/api/hb/member_trade/get")
    public MemberTrade getHb(
            @RequestParam(name = "id") String id
    ) {
        return memberTradeHbService.get(id);
    }

    /**
     * 批量获取HBase中的金流
     * @param ids
     * @returnto
     */
    @GetMapping(value = "/api/hb/member_trade/batch_get")
    public List<MemberTrade> batchGetHb(
            @RequestParam(name = "ids") List<String> ids
    ) {
        return memberTradeHbService.queryList(ids);
    }
}

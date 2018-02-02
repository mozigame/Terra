package com.babel.terra.rest;

import com.babel.common.core.data.RetData;
import com.babel.common.core.page.PageVO;
import com.babel.terra.po.MemberTrade;
import com.babel.terra.rest.service.CashFlowBaseResourceService;
import com.babel.terra.vo.CashFlowBaseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 22:16
 */
@RestController
@RequestMapping("/")
public class CashFlowBaseResource {

    @Resource
    private CashFlowBaseResourceService cashFlowBaseResourceService;

    @GetMapping(value = "/apis/member/trade/list", name = "会员金流查询")
    public RetData<PageVO<MemberTrade>> queryList(
            @RequestParam(name = "orderNo", required = false, defaultValue = "") String orderNo,
            @RequestParam(name = "platInfoId", required = false) Long platInfoId,
            @RequestParam(name = "memberId", required = false) Long memberId,
            @RequestParam(name = "memberName", required = false, defaultValue = "") String memberName,
            @RequestParam(name = "agentId", required = false) Long agentId,
            @RequestParam(name = "agentName", required = false, defaultValue = "") String agentName,
            @RequestParam(name = "tradeType", required = false) Integer tradeType,
            @RequestParam(name = "startTime", required = false) Long startTime,
            @RequestParam(name = "endTime", required = false) Long endTime,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "row", required = false, defaultValue = "15") Integer row
    ) {
        CashFlowBaseVO vo = new CashFlowBaseVO();
        vo.setId(orderNo);
        vo.setPlatInfoId(platInfoId);
        vo.setMemberId(memberId);
        vo.setMemberName(memberName);
        vo.setMemberId(memberId);
        vo.setAgentId(agentId);
        vo.setAgentName(agentName);
        vo.setTradeType(tradeType);
        vo.setStartTime(startTime);
        vo.setEndTime(endTime);
        PageVO<MemberTrade> pageVO = cashFlowBaseResourceService.queryList(vo, page, row);
        return new RetData<>(pageVO);
    }
}

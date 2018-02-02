package com.babel.terra.rest.service;

import com.babel.common.core.page.PageVO;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.po.MemberTrade;
import com.babel.terra.service.CashFlowBaseService;
import com.babel.terra.vo.CashFlowBaseVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 22:16
 */
@Service
public class CashFlowBaseResourceService {

    @Resource
    private CashFlowBaseService cashFlowBaseService;

    public PageVO<MemberTrade> queryList(CashFlowBaseVO base, Integer page, Integer row) {
        PageVO<CashFlowBase> pageVo = new PageVO<>(page, row);
        PageVO<MemberTrade> memberTradePageVO = new PageVO<>(page, row);
        cashFlowBaseService.queryList(base, pageVo);
        if (pageVo.getRows() != null && pageVo.getRows().size() >0) {
            memberTradePageVO.setTotal(pageVo.getTotal());
            List<MemberTrade> memberTrades = new ArrayList<>();
            pageVo.getRows().forEach(cashFlowBase -> {
                memberTrades.add(MemberTrade.parse(cashFlowBase));
            });
            memberTradePageVO.setRows(memberTrades);
        }
        return memberTradePageVO;
    }


}

package com.babel.terra.service;

import com.babel.common.core.page.PageVO;
import com.babel.terra.po.CashFlowBase;
import com.babel.terra.vo.CashFlowBaseVO;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 22:31
 */
public interface CashFlowBaseService {

    boolean save(CashFlowBase cashFlowBase);

    CashFlowBase get(String cashFlowId);

    void queryList(CashFlowBaseVO base, PageVO<CashFlowBase> pageVo);

}

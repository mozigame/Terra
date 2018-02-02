package com.babel.terra.vo;

import com.babel.terra.po.CashFlowBase;

/**
 * User: joey
 * Date: 2018/2/2
 * Time: 0:13
 */

public class CashFlowBaseVO extends CashFlowBase{

    private Long startTime;
    private Long endTime;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}

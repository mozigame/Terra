package com.babel.terra.enums;

/**
 * User: joey
 * Date: 2018/2/1
 * Time: 2:50
 */
public enum BaseFlowType {

    in_out_cash(1), //充提
    bet(2); //投注
    private int code;

    BaseFlowType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

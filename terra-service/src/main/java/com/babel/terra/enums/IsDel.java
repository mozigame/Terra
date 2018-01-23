package com.babel.terra.enums;

/**
 * User: joey
 * Date: 2018/1/22
 * Time: 17:28
 */
public enum IsDel {

    enable(0),
    del(1);

    private int code;

    IsDel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

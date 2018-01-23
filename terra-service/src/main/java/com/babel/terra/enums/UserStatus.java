package com.babel.terra.enums;

/**
 * User: joey
 * Date: 2018/1/22
 * Time: 17:30
 */
public enum UserStatus {

    disable(0),
    used(1);

    private int code;

    UserStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

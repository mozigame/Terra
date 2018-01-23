package com.babel.terra.vo;

import lombok.Data;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * User: joey
 * Date: 2018/1/23
 * Time: 13:59
 */
@Data
public class PageVo<T> {

    private List<T> data;
    private Long total;
    private Integer page;
    private Integer size;

}

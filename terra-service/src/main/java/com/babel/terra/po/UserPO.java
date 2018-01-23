package com.babel.terra.po;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(indexName = "user", type = "user_v1")
@Data
public class UserPO {

    private String id;
    private String name;
    private Integer age;
    private Integer isDel;
    private Integer status;
    private String address;
    private Long createTime;
    private Long modifyTime;
}

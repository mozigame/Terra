package com.babel.terra.po;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "user", type = "user")
@Data
public class UserPO {

    private String id;
    private String name;
    private Integer age;
    private String address;
}

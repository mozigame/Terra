package com.babel.terra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * User: joey
 * Date: 2018/1/19
 * Time: 11:48
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.babel.terra.es")
public class ElasticSearchConfiguration {

}

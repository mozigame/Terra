package com.babel.terra.config;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * User: joey
 * Date: 2018/1/19
 * Time: 11:48
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.babel.terra.es")
public class ElasticSearchConfiguration {

    @Bean
    public ElasticsearchTemplate getElasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }

}

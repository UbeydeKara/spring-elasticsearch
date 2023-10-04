package com.ubeydekara.elasticsearch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.ubeydekara.elasticsearch.repository")
@ComponentScan(basePackages = "com.ubeydekara.elasticsearch")
public class ESConfig {

    @Value("${elastic.host}")
    private String elasticUrl;

    @Bean
    ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo(elasticUrl).build();
    }
}

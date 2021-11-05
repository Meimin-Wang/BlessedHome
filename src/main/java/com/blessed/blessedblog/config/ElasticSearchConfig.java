package com.blessed.blessedblog.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @ClassName ElasticSearchConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/18 : 8:51 下午
 * @Email blessedwmm@gmail.com
 */
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {


    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration.ClientConfigurationBuilderWithRequiredEndpoint builder = ClientConfiguration.builder();
        ClientConfiguration build = builder.connectedTo("www.blessed.com:9200")
                .build();
        return RestClients.create(build).rest();
    }
}

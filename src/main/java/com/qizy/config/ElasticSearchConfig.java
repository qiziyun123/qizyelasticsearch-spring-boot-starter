package com.qizy.config;

import com.qizy.properties.ElasticSearchProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
public class ElasticSearchConfig {

    @Autowired
    private ElasticSearchProperties elasticSearchProperties;

    /**
     *
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        // 校验参数是否
        String hostNames = elasticSearchProperties.getHostnames();
        Integer esPort = elasticSearchProperties.getPort();
        String esScheme = elasticSearchProperties.getScheme();
        String[] esHosts = hostNames.split(",");
        HttpHost[] hosts = new HttpHost[esHosts.length];
        for (int i = 0;i<esHosts.length;i++) {
            HttpHost httpHost = new HttpHost(esHosts[i], esPort, esScheme);
            hosts[i] = httpHost;
        }
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(hosts)
        );

        return client;
    }
}

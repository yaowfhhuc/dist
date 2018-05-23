package me.test.dist.consumer;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;


/**
 *
 *
 *  you can use @SpringCloudApplication to replace the annotations below:
 *      @EnableCircuitBreaker //@EnableHystrix
 *      @EnableDiscoveryClient
 *      @SpringBootApplication
 *
 */
@EnableCircuitBreaker //@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerRibbonApplication {

    //自动负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    //非负载均衡
    @Bean
    @Primary
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerRibbonApplication.class).web(true).run(args);
    }
}
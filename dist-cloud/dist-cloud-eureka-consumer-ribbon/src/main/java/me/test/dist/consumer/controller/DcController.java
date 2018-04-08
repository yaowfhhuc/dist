package me.test.dist.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import me.test.dist.consumer.Hystrix.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String consumer() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }

    @GetMapping("/dc")
    public String dc(){
        return consumerService.consumer();
    }
}
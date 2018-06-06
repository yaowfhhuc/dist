package me.test.dist.consumer.controller;

import me.test.dist.consumer.Hystrix.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {


    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String consumer() {
        return consumerService.consumer();
    }
}
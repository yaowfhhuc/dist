package me.test.dist.consumer.Hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    @Autowired
    RestTemplate restTemplate;//

    @Autowired
    @LoadBalanced
    RestTemplate loadBalanced;

    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer(){
        return loadBalanced.getForObject("http://eureka-client/dc", String.class);
       // return restTemplate.getForObject("http://localhost:xxx/dc", String.class);
    }

    public String fallback(){
        return "fallback";
    }
}

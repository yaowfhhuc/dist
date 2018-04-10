package me.test.dist.consumer.FeignClient;


import me.test.dist.consumer.FeignClient.hystrix.DcClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="eureka-client",fallback = DcClientHystrix.class)
public interface DcClient {

    @GetMapping("/dc")
    String consumer();
}

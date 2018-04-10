package me.test.dist.consumer.FeignClient.hystrix;

import me.test.dist.consumer.FeignClient.DcClient;
import org.springframework.stereotype.Component;

@Component
public class DcClientHystrix implements DcClient {
    @Override
    public String consumer() {
        return "failed";
    }
}

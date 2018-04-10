package me.test.dist.cloud.api.gateway;


import com.netflix.zuul.FilterProcessor;
import me.test.dist.cloud.api.gateway.Filter.AccessFilter;
import me.test.dist.cloud.api.gateway.Filter.ErrorFilter;
import me.test.dist.cloud.api.gateway.processor.DistFilterProcessor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
@EnableZuulProxy
public class ApiGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiGatewayApplication.class).web(true).run(args);
        FilterProcessor.setProcessor(new DistFilterProcessor());
    }


/*    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }*/
}

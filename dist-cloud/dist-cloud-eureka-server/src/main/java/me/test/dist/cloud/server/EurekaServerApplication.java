package me.test.dist.cloud.server;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @use : http://localhost:1001/
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(EurekaServerApplication.class).web(true).run(args);

    }
}

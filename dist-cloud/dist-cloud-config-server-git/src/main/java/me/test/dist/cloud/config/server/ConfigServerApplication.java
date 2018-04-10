package me.test.dist.cloud.config.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @use 查看配置文件: http://localhost:1201/{application}/{profile}[/{label}]
 *    eg. http://localhost:1201/config-client/prod
 *
 *
 *
 *    刷新配置： 启动zookeeper,kafka
 *         eg.  http://localhost:1201/bus/refresh
 *              http://localhost:1201/bus/refresh?destination=config-client
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ConfigServerApplication.class).web(true).run(args);

    }
}

package me.test.dist.cloud.config.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @use : http://localhost:1201/{application}/{profile}[/{label}]
 *    eg. http://localhost:1201/config-client/prod
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ConfigServerApplication.class).web(true).run(args);

    }
}

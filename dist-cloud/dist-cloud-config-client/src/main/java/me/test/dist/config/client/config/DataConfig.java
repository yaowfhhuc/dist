package me.test.dist.config.client.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "info")
public class DataConfig {

    private String profile;

    private String from ;

    public String getFrom() {
        return this.from;
    }

    public String getProfile() {
        return this.profile;
    }

}

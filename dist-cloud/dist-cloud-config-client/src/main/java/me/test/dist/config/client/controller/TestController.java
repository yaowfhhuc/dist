package me.test.dist.config.client.controller;


import me.test.dist.config.client.config.DataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${info.profile}")
    private String profile;

    @Autowired
    private DataConfig dataConfig;

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String profile(){
        return profile;
    }

    @RequestMapping(value = "/from",method = RequestMethod.GET)
    public String from(){
        logger.info(dataConfig.toString());
        return dataConfig.getFrom();
    }
}

package me.test.dist.cloud.client.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @ApiOperation(value = "dc测试")
    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }


    @ApiOperation(value = "根据用户ID获取用户信息")
    @ApiImplicitParam(name = "id",required = true,value = "用户ID",dataType = "String")
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") String id){
        return id;
    }

}
/**   
* @Title: SpingTest.java 
* @Package me.test.dist.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月28日 上午10:30:21 
* @version V1.0   
*/
package me.test.dist.webservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* @ClassName: SpingTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yaowf
* @date 2016年6月28日 上午10:30:21 
*  
*/

@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    private static final String template = "Hello, %s!";

    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        return  String.format(template, name);
    }
	
}

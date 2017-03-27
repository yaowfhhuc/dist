/**   
* @Title: SpingTest.java 
* @Package me.test.dist.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月28日 上午10:30:21 
* @version V1.0   
*/
package me.test.dist.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
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
public class SpingBootTest {

    private static final String template = "Hello, %s!";

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return  String.format(template, name);
    }
	
}

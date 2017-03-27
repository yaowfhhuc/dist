/**   
* @Title: Application.java 
* @Package me.test.dist.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月28日 上午10:45:57 
* @version V1.0   
*/
package org.dist.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
* @ClassName: Application 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yaowf
* @date 2016年6月28日 上午10:45:57 
*  
*/
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

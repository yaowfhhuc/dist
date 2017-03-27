/**   
* @Title: TestScheduler.java 
* @Package me.test.dist.task.scheduler 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月28日 上午10:47:04 
* @version V1.0   
*/
package me.test.dist.task.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
* @ClassName: TestScheduler 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yaowf
* @date 2016年6月28日 上午10:47:04 
*  
*/
@Component
public class TestScheduler {

	@Scheduled(cron="0/5 * * * * *")
	public void testScheduler(){
		System.out.println(new Date());
	}
}

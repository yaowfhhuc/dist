/**   
* @Title: EasysightController.java 
* @Package me.test.dist.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月21日 上午11:15:18 
* @version V1.0   
*/
package me.test.dist.web.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.test.dist.sql.easysight.mapper.ESCheckMsgMapper;

/** 
* @ClassName: EasysightController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yaowf
* @date 2016年6月21日 上午11:15:18 
*  
*/
@Controller
public class EasysightController {

	@Autowired
	private ESCheckMsgMapper checkMsgMapper;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		checkMsgMapper.selectByPrimaryKey(BigDecimal.valueOf(123));
		return "OK";
	}
	
}

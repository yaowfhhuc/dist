package me.test.dist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.test.dist.sql.easysight.mapper.ESMasMsgMapper;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

	@Autowired
	private ESMasMsgMapper esMasMsgMapper;
	
	@RequestMapping("/test")
	public String Exception(){
		String t = null;
		Integer.parseInt(t);
		return "index";
	} 
}

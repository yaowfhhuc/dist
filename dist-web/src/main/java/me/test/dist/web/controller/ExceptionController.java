package me.test.dist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/exception")
public class ExceptionController {


	@RequestMapping("/test")
	public String Exception(){
		String t = null;
		Integer.parseInt(t);
		return "index";
	} 
}

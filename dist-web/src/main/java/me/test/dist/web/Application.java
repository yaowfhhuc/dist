package me.test.dist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		for(String name :context.getBeanDefinitionNames()){
			System.out.println(name);
		}
	}
	
	public static class ServleInit extends SpringBootServletInitializer{
		@Override
		public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(Application.class);
		}
	}
}

package me.test.dist.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@MapperScan(basePackages="me.test.dist.sql.easysight.mapper")
@PropertySources({
	@PropertySource(value="classpath:application.properties"),
	@PropertySource(value="file:../conf/application.properties",ignoreResourceNotFound=true)
})
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		for(String name :context.getBeanDefinitionNames()){
			System.out.println(name);
		}
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	
	public static class ServleInit extends SpringBootServletInitializer{
		@Override
		public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(Application.class);
		}
	}
}

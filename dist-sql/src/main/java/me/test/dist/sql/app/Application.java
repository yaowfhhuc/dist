package me.test.dist.sql.app;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class Application {

	@Bean(name="zgDataSource")
	@ConfigurationProperties(prefix="datasource.ziguan")
	public DataSource zgDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="esDataSource")
	@Primary
	@ConfigurationProperties(prefix="datasource.easysight")
	public DataSource esDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		for(String name :context.getBeanDefinitionNames()){
			System.out.println(name);
		}
	}
	
}

/*package me.test.dist.sql.app;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true),
@PropertySource(value="file:../conf/application.properties",ignoreResourceNotFound=true)})
public class GlobalDataConfig {

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
	
}
*/
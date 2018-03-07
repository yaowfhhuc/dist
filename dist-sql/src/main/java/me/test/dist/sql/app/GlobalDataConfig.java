package me.test.dist.sql.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true),
@PropertySource(value="file:../conf/application.properties",ignoreResourceNotFound=true)})
public class GlobalDataConfig {

	@Bean(name="d1DataSource")
    @Qualifier("d1DataSource")
	@ConfigurationProperties(prefix="spring.datasource.d1")
	public DataSource zgDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="d2DataSource")
	@Primary
    @Qualifier("d2DataSource")
	@ConfigurationProperties(prefix="spring.datasource.d2")
	public DataSource esDataSource(){
		return DataSourceBuilder.create().build();
	}


	@Bean(name = "d1JdbcTemplate")
	public JdbcTemplate d1JdbcTemplage(@Qualifier("d1DataSource")DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }

    @Bean(name = "d2JdbcTemplate")
    public JdbcTemplate d2JdbcTemplage(@Qualifier("d2DataSource")DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }
}

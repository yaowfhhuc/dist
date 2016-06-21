/**   
* @Title: FirstDataSource.java 
* @Package me.test.dist.conf.sql 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月21日 上午10:58:55 
* @version V1.0   
*/
package me.test.dist.conf.sql;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/** 
* @ClassName: FirstDataSource 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yaowf
* @date 2016年6月21日 上午10:58:55 
*  
*/
@Configuration
@MapperScan(basePackages="me.test.dist.sql.easysight.mapper" ,sqlSessionFactoryRef="primarySqlSessionFactory")
public class PrimaryDataSource {

	@Autowired
	private Environment env;
	
	@Bean(name="primaryDs")
	@Qualifier("primaryDs")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.easysight.")
	public DataSource esDataSource() {
		DataSourceBuilder creater = DataSourceBuilder.create();
		creater.driverClassName(env.getProperty("spring.datasource.easysight.driverClassName"))
			.url(env.getProperty("spring.datasource.easysight.url"))
			.username(env.getProperty("spring.datasource.easysight.username"))
			.password(env.getProperty("spring.datasource.easysight.password"));
		DataSource dataSource =creater.build();
		return dataSource;
	}
	
    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager rdsTransactionManager() {
        return new DataSourceTransactionManager(esDataSource());
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory rdsSqlSessionFactory(@Qualifier("primaryDs") DataSource primaryDs) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primaryDs);
        return sessionFactory.getObject();
    }
}

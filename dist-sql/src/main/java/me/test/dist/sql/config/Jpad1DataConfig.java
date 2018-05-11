package me.test.dist.sql.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@AutoConfigureAfter(GlobalDataConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryd1",
        transactionManagerRef="transactionManagerd1",
        basePackages= { "me.test.dist.sql.jpa.d1.repository" }) //设置Repository所在位置
public class Jpad1DataConfig {

    @Autowired
    @Qualifier("d1DataSource")
    private DataSource primaryDataSource;

/*    @Autowired
    private JpaProperties jpaProperties;*/

    @Primary
    @Bean(name = "entityManagerd1")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryd1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                //.properties(getVendorProperties(primaryDataSource))
                .packages("me.test.dist.sql.jpa.d1.pojo") //设置实体类所在位置
                .persistenceUnit("d1PersistenceUnit")
                .build();
    }


 /*   private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }*/

    @Primary
    @Bean(name = "transactionManagerd1")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }


}

package com.sca.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.persistence.*;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.jdbc.DataSourceBuilder;
import com.sca.entity.User;
 
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManager",
        transactionManagerRef = "mysqlTransactionManager",
        basePackages = "com.sca.repository.UserRepository"
)
public class MySqlConfiguration {
 
	@Primary
	@Bean(name="userProps")
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties dataSourceProperties() {
	    return new DataSourceProperties();
	}

	@Primary
	@Bean(name="datasource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource datasource(@Qualifier("userProps") DataSourceProperties properties){
	    return properties.initializeDataSourceBuilder().build();
	}

	@Primary
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
	        (EntityManagerFactoryBuilder builder,
	         @Qualifier("datasource") DataSource dataSource){
	    return builder.dataSource(dataSource)
	            .packages("com.sca.entity.User")
	            .persistenceUnit("User").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	@ConfigurationProperties("spring.jpa")
	public PlatformTransactionManager transactionManager(
	        @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
	    return new JpaTransactionManager(entityManagerFactory);
	}
    

}

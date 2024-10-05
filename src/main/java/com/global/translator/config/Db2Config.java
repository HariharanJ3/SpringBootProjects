package com.global.translator.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "productentityManagerFactory", transactionManagerRef = "producttransactionManager", basePackages = {"com.global.translator.product.repositories"})
//@EntityScan("com.global.translator.model")
public class Db2Config {

	
//	@Bean( name = "productdataSource" )
//	@ConfigurationProperties( prefix = "db1.datasource" )
//	public DataSource productdataSource()
//	{
//	    return DataSourceBuilder.create().build();
//	}
//
//	@Bean( name = "productentityManagerFactory" )
//	public LocalContainerEntityManagerFactoryBean productentityManagerFactory( EntityManagerFactoryBuilder builder,
//	        @Qualifier( "productdataSource" ) DataSource dataSource )
//	{
//	    return builder.dataSource(dataSource).packages("com.global.translator.model")
//	            .persistenceUnit("db2").build();
//	}
//
//	@Bean( name = "producttransactionManager" )
//	public PlatformTransactionManager transactionManager(
//	        @Qualifier( "productentityManagerFactory" ) EntityManagerFactory entityManagerFactory )
//	{
//	    return new JpaTransactionManager(entityManagerFactory);
//	}

}

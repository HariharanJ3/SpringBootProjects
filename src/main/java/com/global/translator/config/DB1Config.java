/**
 * 
 */
package com.global.translator.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hariharan J
 *
 * Jun 22, 2023
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "userentityManagerFactory", transactionManagerRef = "usertransactionManager", basePackages = {"com.global.translator.repositories"})
//@EntityScan("com.global.translator.model")
public class DB1Config {
	
//	@Primary
//	@Bean( name = "dataSource" )
//	@ConfigurationProperties( prefix = "spring.datasource" )
//	public DataSource dataSource()
//	{
//	    return DataSourceBuilder.create().build();
//	}
//
//	@Primary
//	@Bean( name = "userentityManagerFactory" )
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory( EntityManagerFactoryBuilder builder,
//	        @Qualifier( "dataSource" ) DataSource dataSource )
//	{
//	    return builder.dataSource(dataSource).packages("com.global.translator.model")
//	            .persistenceUnit("db").build();
//	}
//
//	@Primary
//	@Bean( name = "usertransactionManager" )
//	public PlatformTransactionManager transactionManager(
//	        @Qualifier( "userentityManagerFactory" ) EntityManagerFactory entityManagerFactory )
//	{
//	    return new JpaTransactionManager(entityManagerFactory);
//	}
	
	public int x = 5, y = 10;
	
	public static String stat = "hiii";
	
	public static void name()
	{
		System.out.println("Hariharan J");
	}
	
	public void instanMethod()
	{
		System.out.println("instanMethod in DBconfig");
	}
	
	public DB1Config()
	{
		System.out.println("Parent Class");
	}
}

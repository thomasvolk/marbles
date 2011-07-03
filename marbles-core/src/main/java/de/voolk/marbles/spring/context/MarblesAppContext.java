package de.voolk.marbles.spring.context;

import de.voolk.marbles.app.AppConfig;
import de.voolk.marbles.persistence.services.ApplicationInitialisationService;
import de.voolk.marbles.persistence.services.IApplicationInitialisationService;
import de.voolk.marbles.utils.Digest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class MarblesAppContext {
	@Bean
	public AppConfig config() {
		return new AppConfig();
	}
	
	@Bean
	public Digest passwordDigest() {
		return new Digest();
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(config().get("database.driver"));
		// jdbc:hsqldb:mem:aname
		dataSource.setUrl(config().getProperties().getProperty("database.url"));
		dataSource.setUsername(config().get("database.user"));
		dataSource.setPassword(config().get("database.password"));
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean = 
			new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		return localSessionFactoryBean;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.valueOf(
				config().get("database.database")));
		adapter.setShowSql(config().getBoolean("database.showSql"));
		adapter.setGenerateDdl(config().getBoolean("database.generateDdl"));
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = 
			new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("punit");
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		return factory;
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
				entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public PersistenceAnnotationBeanPostProcessor 
	persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
	
	@Bean
	public IApplicationInitialisationService applicationInitialisationService() {
		return new ApplicationInitialisationService();
	}
}

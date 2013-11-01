/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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

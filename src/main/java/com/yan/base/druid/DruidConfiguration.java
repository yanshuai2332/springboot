package com.yan.base.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author YAN
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@ConfigurationProperties
public class DruidConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);


	@Autowired
	private DruidProperties druidProperties;

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(druidProperties.getUrl());
		datasource.setUsername(druidProperties.getUsername());
		datasource.setPassword(druidProperties.getPassword());
		datasource.setDriverClassName(druidProperties.getDriverClassName());

		//configuration
		datasource.setInitialSize(druidProperties.getInitialSize());
		datasource.setMinIdle(druidProperties.getMinIdle());
		datasource.setMaxActive(druidProperties.getMaxActive());
		datasource.setMaxWait(druidProperties.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(druidProperties.getValidationQuery());
		datasource.setTestWhileIdle(druidProperties.isTestWhileIdle());
		datasource.setTestOnBorrow(druidProperties.isTestOnBorrow());
		datasource.setTestOnReturn(druidProperties.isTestOnReturn());
		datasource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
		datasource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
		datasource.setConnectionProperties(druidProperties.getConnectionProperties());

		try {
			datasource.setFilters(druidProperties.getFilters());
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}

		return datasource;
	}

}

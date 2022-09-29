package org.test.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    private final Environment env;

    @Autowired
    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean("postgresDataSource")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(this.env.getProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(this.env.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(this.env.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(this.env.getProperty("spring.datasource.password"));
        return dataSourceBuilder.build();
    }
}

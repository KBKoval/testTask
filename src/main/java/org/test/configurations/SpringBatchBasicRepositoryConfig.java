package org.test.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class SpringBatchBasicRepositoryConfig implements BatchConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchBasicRepositoryConfig.class);
    @Qualifier("postgresDataSource")
    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public SpringBatchBasicRepositoryConfig(DataSource dataSource, PlatformTransactionManager transactionManager) {
        this.dataSource = dataSource;
        this.transactionManager = transactionManager;
    }

    @Override
    public JobRepository getJobRepository() throws Exception {
        LOGGER.info("DataSource: " + dataSource);
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(getTransactionManager());
        factory.setTablePrefix("JOBS");
        factory.setMaxVarCharLength(1200);
        return factory.getObject();
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
        LOGGER.info("getTransactionManager()" + dataSource);
        PlatformTransactionManager trm = new DataSourceTransactionManager(dataSource);
        return trm;
    }


    @Override
    @Bean("asycJobLauncher")
    public JobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.setTaskExecutor(taskExecutor());
        jobLauncher.afterPropertiesSet();
        LOGGER.info("JobLauncher Async");
        return jobLauncher;
    }
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

  

    @Override
    public JobExplorer getJobExplorer() throws Exception {
        return null;
    }
}

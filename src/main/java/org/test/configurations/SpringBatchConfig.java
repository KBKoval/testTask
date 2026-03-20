package org.test.configurations;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.test.listener.SpringBatchJobCompletionListener;
import org.test.listener.SpringBatchJobExecutionListener;
import org.test.listener.SpringBatchStepListener;
import org.test.models.FileInfoDto;
import org.test.processor.InfoProcessor;
import org.test.reader.ExcelReader;
import org.test.services.interfaces.SectionsServiceJDBC;
import org.test.writer.ExcelWriter;

import java.util.List;
import java.util.function.Function;

@Configuration
@EnableAsync
public class SpringBatchConfig {

  @Bean
  public Job processJob(JobRepository jobRepository, @Qualifier("step") Step step) {
    return new JobBuilder("stockpricesinfojob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(new SpringBatchJobExecutionListener())
        .start(step)
        .build();
  }

  @Bean
  public ExcelReader excelReader() {
    return new ExcelReader();
  }

  @Bean
  public Step step(JobRepository jobRepository) {
    return new StepBuilder("step", jobRepository)
        .listener(new SpringBatchStepListener())
        .<FileInfoDto, FileInfoDto>chunk(10)
        .reader(excelReader())
        .processor(excellProcessor())
        .writer(writer())
        .faultTolerant()
        .retryLimit(3)
        .retry(Exception.class)
        .build();
  }



  @Bean
  public InfoProcessor excellProcessor() {
    return new InfoProcessor();
  }

  @Bean
  public ExcelWriter writer() {
    return new ExcelWriter();
  }

  @Bean
  public JobExecutionListener listener() {
    return new SpringBatchJobCompletionListener();
  }
}

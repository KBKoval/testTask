package org.test.configurations;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
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
    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    public final SectionsServiceJDBC service;

    @Autowired
    public SpringBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, SectionsServiceJDBC service) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.service = service;
    }

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("stockpricesinfojob")
                .incrementer(new RunIdIncrementer())
                .listener(new SpringBatchJobExecutionListener())
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("Read -> Store -> Aggregate-Transform")
                .listener(new SpringBatchStepListener())
                .<FileInfoDto, FileInfoDto>chunk(10)
                .reader(excelReader())
                .processor(  excellProcessor())
                .writer(writer())
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .build();
    }

    @Bean
    public ExcelReader excelReader() {
        return new ExcelReader();
    }

    @Bean
    public ItemProcessor excellProcessor(){
        return new InfoProcessor();
    }

    @Bean
    public ItemWriter writer() {
        return new ExcelWriter();
    }

    @Bean
    public JobExecutionListener listener() {
        return new SpringBatchJobCompletionListener();
    }
}

package org.test.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;


public class SpringBatchJobExecutionListener implements JobExecutionListener {
    Logger logger = LoggerFactory.getLogger(SpringBatchJobExecutionListener.class);

    public void beforeJob(JobExecution jobExecution) {
        logger.info("BEFORE BATCH JOB : "+jobExecution.getJobInstance().getInstanceId());
    }

    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("BATCH JOB COMPLETED SUCCESSFULLY");
        }else if(jobExecution.getStatus() == BatchStatus.FAILED){
            logger.info("BATCH JOB FAILED");
        }
    }
}

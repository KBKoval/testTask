package org.test.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.step.StepExecution;

public class SpringBatchStepListener implements StepExecutionListener {
    Logger logger = LoggerFactory.getLogger(SpringBatchStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
      //  logger.info("SPStepListener - CALLED BEFORE STEP.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
   //     logger.info("SPStepListener - CALLED AFTER STEP.");
        return ExitStatus.COMPLETED;
    }
}

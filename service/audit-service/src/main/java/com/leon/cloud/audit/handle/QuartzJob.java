package com.leon.cloud.audit.handle;

import lombok.extern.log4j.Log4j2;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.TimeUnit;

@Log4j2
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("quartz starting");
        try {
            TimeUnit.MILLISECONDS.sleep(6*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("quartz end");
    }
}

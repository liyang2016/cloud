package com.leon.cloud.audit.config;

import com.leon.cloud.audit.handle.QuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder.newJob(QuartzJob.class).withIdentity("quartzJob").storeDurably().build();
    }

    @Bean
    public Trigger quartzJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(quartzJobDetail()).withIdentity("quartzJob").withSchedule(cronScheduleBuilder).build();
    }
}

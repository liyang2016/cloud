package com.leon.cloud.audit.handle;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Log4j2
public class ScheduleHandle {
    private List<Integer> index = Arrays.asList(8 * 1000, 3 * 1000, 6 * 1000, 2 * 1000, 2 * 1000);
    private AtomicInteger atomicInteger = new AtomicInteger(0);

//    @Scheduled(fixedDelay = 3 * 1000)
//    @Scheduled(cron = "0/5 * * * * ?")
//    @Scheduled(fixedRate = 3 * 1000)
    public void scheduled() {
        int i = atomicInteger.get();
        if (i < 5) {
            Integer sleepTime = index.get(i);
            try {
                log.info("第{}个任务开始执行，执行时间{}ms", i, sleepTime);
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                atomicInteger.getAndIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

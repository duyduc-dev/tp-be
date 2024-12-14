package com.learn.techplatform.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private final CleanSessionWorker cleanSessionWorker;

    public SchedulerService(CleanSessionWorker cleanSessionWorker) {
        this.cleanSessionWorker = cleanSessionWorker;
    }

    @Scheduled(fixedDelay = 86400000)
    public void cleanSession() {
        cleanSessionWorker.doWork();
    }
}

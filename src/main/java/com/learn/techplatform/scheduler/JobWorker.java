package com.learn.techplatform.scheduler;

import org.springframework.stereotype.Component;

@Component
public abstract class JobWorker {
    public abstract JobType getJobType();

    public abstract String getJobName();

    public abstract Boolean isQueueEmpty();

    public abstract void doWork();

    public enum JobType {
        // Allow run single thread for each schedule
        SINGLE,
        // Allow run multi-thread for each schedule & manage by thread pool size
        MULTIPLE
    }
}

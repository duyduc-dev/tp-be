package com.learn.techplatform.scheduler;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.utils.DateUtil;
import com.learn.techplatform.entities.Session;
import com.learn.techplatform.security.AuthHelper;
import com.learn.techplatform.services.Session.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class CleanSessionWorker extends JobWorker{

    private final SessionService sessionService;
    private final AuthHelper authHelper;

    public CleanSessionWorker(SessionService sessionService, AuthHelper authHelper) {
        this.sessionService = sessionService;
        this.authHelper = authHelper;
    }

    @Override
    public JobType getJobType() {
        return JobType.SINGLE;
    }

    @Override
    public String getJobName() {
        return "CleanSessionWorker";
    }

    @Override
    public Boolean isQueueEmpty() {
        return false;
    }

    @Override
    public void doWork() {
        long time = new Date().getTime() - 86400000;
        List<Session> session = sessionService.getAll();
        session = session.stream()
                .filter(item ->
                    item.getSystemStatus() == SystemStatus.INACTIVE
                    || authHelper.isSessionExpired(item)
                )
                .toList();
        log.info("CleanSessionWorker worker {} - {} - size: {}", time, new Date(time), session.size());
        if(!session.isEmpty()) {
            sessionService.deleteAll(session);
        }
    }
}

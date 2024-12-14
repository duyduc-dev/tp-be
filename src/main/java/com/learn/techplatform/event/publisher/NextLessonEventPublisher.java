package com.learn.techplatform.event.publisher;

import com.learn.techplatform.event.NextLessonEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NextLessonEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public NextLessonEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishNextLessonEvent(final String userId, final String nextLessonId) {
        System.out.println("Publishing custom event. ");
        NextLessonEvent nextLessonEvent = new NextLessonEvent(this, userId, nextLessonId);
        applicationEventPublisher.publishEvent(nextLessonEvent);
    }
}

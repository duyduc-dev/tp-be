package com.learn.techplatform.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NextLessonEvent extends ApplicationEvent {

    private final String userId;
    private final String nextLessonId;

    public NextLessonEvent(Object source, String userId, String nextLessonId) {
        super(source);
        this.userId = userId;
        this.nextLessonId = nextLessonId;
    }

}

package com.learn.techplatform.event.listener;

import com.learn.techplatform.dto_modals.CourseDTO;
import com.learn.techplatform.entities.UserCourse;
import com.learn.techplatform.event.NextLessonEvent;
import com.learn.techplatform.services.Course.CourseService;
import com.learn.techplatform.services.UserCourse.UserCourseService;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NextLessonEventListener implements ApplicationListener<NextLessonEvent> {

    private final UserCourseService userCourseService;
    private final CourseService courseService;

    public NextLessonEventListener(UserCourseService userCourseService, CourseService courseService) {
        this.userCourseService = userCourseService;
        this.courseService = courseService;
    }

    @Async
    @Override
    public void onApplicationEvent(NextLessonEvent event) {
        CourseDTO courseDTO = this.courseService.getCourseByLessonId(event.getNextLessonId());
        UserCourse userCourse = this.userCourseService.getByUserIdAndCourseId(event.getUserId(), courseDTO.getId(), false);
        if(userCourse == null) {
            return;
        }
        userCourse.setCurrentLessonId(event.getNextLessonId());
        this.userCourseService.save(userCourse);

    }
}

package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.entities.Course;
import com.learn.techplatform.entities.UserCourse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class UserCourseDTO {
    private String id;
    private String userId;
    private String courseId;
    private String currentLessonId;
    private CourseDTO course;
    private Date createdAt;
    private Date updatedAt;

    public UserCourseDTO(UserCourse userCourse, Course course) {
        this.id = userCourse.getId();
        this.userId = userCourse.getUserId();
        this.course = new CourseDTO(course);
        this.createdAt = userCourse.getCreatedDate();
        this.updatedAt = userCourse.getUpdatedDate();
        this.currentLessonId = userCourse.getCurrentLessonId();
    }

    public UserCourseDTO(UserCourse userCourse) {
        this.id = userCourse.getId();
        this.userId = userCourse.getUserId();
        this.courseId = userCourse.getCourseId();
        this.createdAt = userCourse.getCreatedDate();
        this.updatedAt = userCourse.getUpdatedDate();
        this.currentLessonId = userCourse.getCurrentLessonId();
    }
}

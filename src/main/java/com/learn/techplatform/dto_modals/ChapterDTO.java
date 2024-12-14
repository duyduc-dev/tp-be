package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.controllers.models.request.NewChapterRequest;
import com.learn.techplatform.entities.Chapter;
import com.learn.techplatform.entities.Course;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class ChapterDTO  {
    private String id;
    private String title;
    private String previousChapterId;
    private String courseId;
    private int position;
    private long lessonCount;

    private SystemStatus systemStatus;
    private Date createAt;
    private Date updateAt;
    private CourseDTO course;

    private List<LessonDTO> lessons;

    public ChapterDTO(Chapter chapter, long lessonCount) {
        this.id = chapter.getId();
        this.title = chapter.getTitle();
        this.previousChapterId = chapter.getPreviousChapterId();
        this.courseId = chapter.getCourseId();
        this.updateAt = chapter.getUpdatedDate() ;
        this.systemStatus = chapter.getSystemStatus();
        this.createAt = chapter.getCreatedDate();
        this.lessonCount = lessonCount;
    }

    public ChapterDTO(Chapter chapter) {
        this.id = chapter.getId();
        this.title = chapter.getTitle();
        this.previousChapterId = chapter.getPreviousChapterId();
        this.courseId = chapter.getCourseId();
        this.updateAt = chapter.getUpdatedDate() ;
        this.systemStatus = chapter.getSystemStatus();
        this.createAt = chapter.getCreatedDate();
    }

    public ChapterDTO(Chapter chapter, Course course) {
        this.id = chapter.getId();
        this.title = chapter.getTitle();
        this.previousChapterId = chapter.getPreviousChapterId();
        this.courseId = chapter.getCourseId();
        this.updateAt = chapter.getUpdatedDate() ;
        this.systemStatus = chapter.getSystemStatus();
        this.createAt = chapter.getCreatedDate();
        this.course = this.fromCourse(course);
    }

    public ChapterDTO(NewChapterRequest newChapterRequest) {
        this.title = newChapterRequest.getTitle();
        this.previousChapterId = newChapterRequest.getPreviousChapterId();
        this.courseId = newChapterRequest.getCourseId();
    }

    private CourseDTO fromCourse(Course course) {
        return CourseDTO.builder()
                .code(course.getCode())
                .title(course.getTitle())
                .slug(course.getSlug())
                .price(course.getPrice())
                .description(course.getDescription())
                .discount(course.getDiscount())
                .build();
    }
}

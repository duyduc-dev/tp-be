package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.CourseType;
import com.learn.techplatform.controllers.models.request.EditCourseRequest;
import com.learn.techplatform.entities.Course;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class CourseDTO {
    private String id;
    private String title;
    private String thumbnailUrl;
    private String slug;
    private int viewed;
    private String description;
    private String currentLessonId;
    private float price;
    private String content;
    private String code;
    private CourseType courseType;
    private float discount;
    private boolean isRegistered = false;
    private Date createdAt;
    private Date updatedAt;

    private List<ChapterDTO> chapters;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.thumbnailUrl = course.getThumbnailUrl();
        this.slug = course.getSlug();
        this.viewed = course.getViewed();
        this.description = course.getDescription();
        this.price = course.getPrice();
        this.content = course.getContent();
        this.courseType = course.getCourseType();
        this.code = course.getCode();
        this.discount = course.getDiscount();
        this.createdAt = course.getCreatedDate();
        this.updatedAt = course.getUpdatedDate();
    }

    public CourseDTO(Course course, Boolean isRegistered, String currentLessonId) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.thumbnailUrl = course.getThumbnailUrl();
        this.slug = course.getSlug();
        this.viewed = course.getViewed();
        this.description = course.getDescription();
        this.price = course.getPrice();
        this.content = course.getContent();
        this.courseType = course.getCourseType();
        this.code = course.getCode();
        this.discount = course.getDiscount();
        this.createdAt = course.getCreatedDate();
        this.updatedAt = course.getUpdatedDate();
        this.isRegistered = isRegistered;
        this.currentLessonId = currentLessonId;
    }


    public CourseDTO(EditCourseRequest editCourseRequest) {
        this.title = editCourseRequest.getTitle();
        this.thumbnailUrl = editCourseRequest.getThumbnailUrl();
        this.description = editCourseRequest.getDescription();
        this.price = editCourseRequest.getPrice();
        this.content = editCourseRequest.getContent();
        this.courseType = editCourseRequest.getCourseType();
        this.discount = editCourseRequest.getDiscount();
    }
}

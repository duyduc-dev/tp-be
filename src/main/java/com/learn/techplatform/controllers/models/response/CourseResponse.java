package com.learn.techplatform.controllers.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.CourseType;
import com.learn.techplatform.common.utils.AppUtil;
import com.learn.techplatform.common.utils.AppValueConfigure;
import com.learn.techplatform.entities.Course;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class CourseResponse {
    private String title;
    private String id;
    private String code;
    private String thumbnailUrl;
    private String imgPublicId;
    private String slug;
    private int viewed;
    private String description;
    private float price;
    private String content;
    private CourseType courseType;
    private float discount;

    public CourseResponse(Course course, AppValueConfigure appValueConfigure) {
        this.title = course.getTitle();
        this.id = course.getId();
        this.code = course.getCode();
        this.thumbnailUrl = AppUtil.getUrlFile(appValueConfigure, course.getThumbnailUrl());
        this.imgPublicId = course.getImgPublicId();
        this.slug = course.getSlug();
        this.viewed = course.getViewed();
        this.description = course.getDescription();
        this.price = course.getPrice();
        this.content = course.getContent();
        this.courseType = course.getCourseType();
        this.discount = course.getDiscount();

    }
}

package com.learn.techplatform.controllers.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.CourseType;
import com.learn.techplatform.common.enums.LessonType;
import com.learn.techplatform.common.utils.ParamError;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditLessonRequest {
    private String title;

    private String thumbnailUrl;

    private long duration;

    @NotBlank(message = ParamError.FIELD_NAME)
    private String lessonType;

    private String content;

    private String question;

    private int numericalOrder;

    private String chapterId;

    private String videoId;
}

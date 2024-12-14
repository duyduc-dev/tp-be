package com.learn.techplatform.controllers.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.LessonType;
import com.learn.techplatform.common.utils.ParamError;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewLessonRequest {
    @NotBlank(message = ParamError.FIELD_NAME)
    private String title;
    private LessonType type;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String thumbnailUrl;
    private String content;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String chapterId;
    private String previousLessonId;
    private long duration;

    private String code;

    // video
    private String videoId;

    // question
    private String question;
    private List<LessonAnswerRequest> answers;

    // document
    private String document;
}

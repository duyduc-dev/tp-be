package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.controllers.models.request.LessonAnswerRequest;
import com.learn.techplatform.entities.LessonAnswer;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class LessonAnswerDTO {
    private String id;
    private String lessonId;
    private String title;
    private String description;
    private boolean isCorrect;
    private long createdAt;
    private long updatedAt;

    public LessonAnswerDTO(LessonAnswer lessonAnswer) {
        this.id = lessonAnswer.getId();
        this.lessonId = lessonAnswer.getLessonId();
        this.title = lessonAnswer.getTitle();
        this.description = lessonAnswer.getDescription();
        this.isCorrect = lessonAnswer.isCorrect();
        this.createdAt = lessonAnswer.getCreatedDate().getTime();
        this.updatedAt = lessonAnswer.getUpdatedDate().getTime();
    }

    public LessonAnswerDTO(LessonAnswerRequest lessonAnswerRequest) {
        this.lessonId = lessonAnswerRequest.getLessonId();
        this.description = lessonAnswerRequest.getDescription();
        this.title = lessonAnswerRequest.getTitle();
        this.isCorrect = lessonAnswerRequest.isCorrect();
    }

    public static List<LessonAnswerDTO> toList(List<LessonAnswerRequest> lessonAnswerRequests) {
        return lessonAnswerRequests.stream().map(LessonAnswerDTO::new).toList();
    }
}

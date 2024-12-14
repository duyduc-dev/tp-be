package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.LessonStatus;
import com.learn.techplatform.common.enums.LessonType;
import com.learn.techplatform.controllers.models.request.CreateLessonRequest;
import com.learn.techplatform.controllers.models.request.EditLessonRequest;
import com.learn.techplatform.controllers.models.request.NewLessonRequest;
import com.learn.techplatform.entities.Lesson;
import com.learn.techplatform.entities.LessonQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class LessonDTO {
    private String id;
    private String title;
    private String slug;    
    private String thumbnailUrl;
    private long duration;
    private long order;
    private LessonType lessonType;
    private LessonStatus lessonStatus;
    private String content;
    private String question;
    private String chapterId;
    private String videoId;
    private String document;
    private Date createdAt;
    private Date updatedAt;

    private String previousLessonId;
    private String nextLessonId;
    private String code;
    private String tpEditorUrl;
    private List<LessonAnswerDTO> answers;

    public LessonDTO(Lesson lesson) {
        this.id = lesson.getId();

        this.title = lesson.getTitle();
        this.slug = lesson.getSlug();
        this.duration = lesson.getDuration();
        this.chapterId = lesson.getChapterId();
        this.lessonType = lesson.getLessonType();
        this.lessonStatus = lesson.getLessonStatus();
        this.content = lesson.getContent();
        this.question = lesson.getQuestion();
        this.videoId = lesson.getVideoId();
        this.document = lesson.getDocument();
        this.previousLessonId = lesson.getPreviousLessonId();
        this.code = lesson.getCode();
        this.answers = new ArrayList<>();

        this.createdAt = lesson.getCreatedDate();
        this.updatedAt = lesson.getUpdatedDate();
        this.tpEditorUrl = lesson.getTpEditorUrl();

    }

    public LessonDTO(NewLessonRequest newLessonRequest) {
        this.title = newLessonRequest.getTitle();
        this.thumbnailUrl = newLessonRequest.getThumbnailUrl();
        this.duration = newLessonRequest.getDuration();
        this.content = newLessonRequest.getContent();
        this.previousLessonId = newLessonRequest.getPreviousLessonId();
        this.lessonType = newLessonRequest.getType();
        this.chapterId = newLessonRequest.getChapterId();
        this.code = newLessonRequest.getCode();
        this.videoId = newLessonRequest.getVideoId();

        this.question = newLessonRequest.getQuestion();
        this.answers = newLessonRequest.getAnswers() == null ? new ArrayList<>() : LessonAnswerDTO.toList(newLessonRequest.getAnswers());

        this.document = newLessonRequest.getDocument();
    }


    public void ignoreMainData() {
        this.content = null;
        this.videoId = null;
        this.question = null;
        this.answers = null;
        this.document = null;
        this.thumbnailUrl = null;
        this.lessonType = null;
    }


    public static LessonDTO create(Lesson lesson) {
        return new LessonDTO(lesson);
    }
}

package com.learn.techplatform.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.techplatform.common.enums.LessonStatus;
import com.learn.techplatform.common.enums.LessonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@EntityListeners(AuditingEntityListener.class)
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "[lesson]")
public class Lesson extends AbstractBaseEntity<String> implements Serializable {
    @Column(name = "title")
    private String title;

    @Column(name = "slug", columnDefinition = "TEXT")
    private String slug;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "duration")
    private long duration;

    @Column(name = "lesson_type")
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @Column(name = "lesson_status")
    @Enumerated(EnumType.STRING)
    private LessonStatus lessonStatus;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @Column(name = "document", columnDefinition = "TEXT")
    private String document;

    @Column(name = "chapter_id")
    private String chapterId;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "prev_lesson_id")
    private String previousLessonId;

    @Column(name = "code")
    private String code;

    @Column(name = "numericalOrder")
    private int numericalOrder;

    @Column(name = "tp_editor_url", nullable = true)
    private String tpEditorUrl;

    public boolean isDone() {
        return LessonStatus.DONE.equals(lessonStatus);
    }

    public boolean isUnlocked() {
        return LessonStatus.UNLOCKED.equals(lessonStatus);
    }
}

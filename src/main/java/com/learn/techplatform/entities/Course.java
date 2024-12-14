package com.learn.techplatform.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.CourseType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "[course]")
public class Course extends AbstractBaseEntity<String> implements Serializable {
    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "img_public_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String imgPublicId;

    @Column(name = "slug", columnDefinition = "TEXT")
    private String slug;

    @Column(name = "viewed")
    private int viewed;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "course_type")
    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @Column(name = "discount")
    private float discount;

    @Column(name = "created_by_user_id")
    private String createdByUserId;
}

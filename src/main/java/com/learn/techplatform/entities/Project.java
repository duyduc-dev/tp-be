package com.learn.techplatform.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.techplatform.common.enums.ProjectMode;
import com.learn.techplatform.common.enums.ProjectType;
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
@Table(name = "[project]")
public class Project extends AbstractBaseEntity<String> implements Serializable {
    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Column(name = "mode")
    @Enumerated(EnumType.STRING)
    private ProjectMode mode;

    @Column(name = "is_template", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isTemplate;
}

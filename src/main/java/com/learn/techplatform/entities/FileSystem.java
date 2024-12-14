package com.learn.techplatform.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.techplatform.common.enums.FileExploreType;
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
@Table(name = "[file]")
public class FileSystem extends AbstractBaseEntity<String> implements Serializable {
    @Column(name = "project_id")
    private String projectId;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FileExploreType type;
}

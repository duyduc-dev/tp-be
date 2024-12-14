package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.ProjectMode;
import com.learn.techplatform.common.enums.ProjectType;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.entities.Project;
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
public class ProjectDTO {
    private String id;
    private String title;
    private String description;
    private String userId;
    private String slug;
    private ProjectType type;
    private ProjectMode mode;
    private Date createAt;
    private Date updateAt;
    private boolean isTemplate;
    private SystemStatus systemStatus;
    private List<FileExploreDTO> fileExplore;

    public ProjectDTO(Project project) {
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.userId = project.getUserId();
        this.type = project.getType();
        this.mode = project.getMode();
        this.systemStatus = project.getSystemStatus();
        this.createAt = project.getCreatedDate();
        this.updateAt = project.getUpdatedDate();
        this.slug = project.getSlug();
        this.isTemplate = project.isTemplate();
    }

    public ProjectDTO(Project project, List<FileExploreDTO> fileExplore) {
        this.id = project.getId();  
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.userId = project.getUserId();
        this.type = project.getType();
        this.mode = project.getMode();
        this.systemStatus = project.getSystemStatus();
        this.createAt = project.getCreatedDate();
        this.updateAt = project.getUpdatedDate();
        this.fileExplore = fileExplore;
        this.slug = project.getSlug();
        this.isTemplate = project.isTemplate();
    }
}

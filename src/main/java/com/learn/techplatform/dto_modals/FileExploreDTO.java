package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.FileExploreType;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.utils.UniqueID;
import com.learn.techplatform.controllers.models.request.FileExploreRequest;
import com.learn.techplatform.entities.FileSystem;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class FileExploreDTO {
    private String id;
    private String projectId;
    private String name;
    private String parentId;
    private String content;
    private Integer depth;
    private String path;
    private FileExploreType type;
    private SystemStatus systemStatus;
    private Date createAt;
    private Date updateAt;


    public FileExploreDTO(FileSystem file) {
        this.id = file.getId();
        this.projectId = file.getProjectId();
        this.name = file.getName();
        this.parentId = file.getParentId();
        this.content = file.getContent();
        this.depth = file.getDepth();
        this.path = file.getPath();
        this.type = file.getType();
        this.systemStatus = file.getSystemStatus();
        this.createAt = file.getCreatedDate();
        this.updateAt = file.getUpdatedDate();
    }


    public FileExploreDTO(FileExploreRequest file) {
        this.projectId = file.getProjectId();
        this.name = file.getName();
        this.parentId = file.getParentId();
        this.content = file.getContent();
        this.depth = file.getDepth();
        this.path = file.getPath();
        this.type = file.getType();
    }

    public FileSystem newFile() {
        return FileSystem.builder()
                .id(UniqueID.getUUID())
                .content(this.content)
                .projectId(this.projectId)
                .name(this.name)
                .parentId(this.parentId)
                .type(this.type)
                .systemStatus(SystemStatus.ACTIVE)
                .depth(this.depth)
                .build();
    }
}

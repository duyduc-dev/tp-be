package com.learn.techplatform.helper;

import com.learn.techplatform.common.enums.FileExploreType;
import com.learn.techplatform.dto_modals.FileExploreDTO;
import com.learn.techplatform.entities.FileSystem;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FileExploreHelper {


    public void editFileExplore(FileSystem file, FileExploreDTO request) {
        if(request == null) {
            return;
        }
        if(request.getName() != null && !request.getName().trim().isEmpty()) {
            file.setName(request.getName().trim());
        }
        if(request.getPath() != null && !request.getPath().trim().isEmpty()) {
            file.setPath(request.getPath().trim());
        }
        if(request.getDepth() != null && request.getDepth() >= 0) {
            file.setDepth(request.getDepth());
        }
        if(request.getParentId() != null && request.getParentId().trim().isEmpty()) {
            file.setParentId(request.getParentId());
        }
        if(request.getContent() != null && request.getContent().trim().isEmpty()) {
            file.setContent(request.getContent());
        }
        if(request.getProjectId() != null && request.getProjectId().trim().isEmpty()) {
            file.setProjectId(request.getProjectId());
        }
        if(request.getType() != null && Arrays.stream(FileExploreType.values()).toList().contains(request.getType())) {
            file.setType(request.getType());
        }
    }
}

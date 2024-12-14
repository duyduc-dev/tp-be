package com.learn.techplatform.services.File;

import com.learn.techplatform.dto_modals.FileExploreDTO;
import com.learn.techplatform.entities.FileSystem;
import com.learn.techplatform.services.InterfaceBaseService;

import java.io.File;
import java.util.List;

public interface FileService extends InterfaceBaseService<FileSystem, String> {

    List<FileExploreDTO> getFileExploreDTOByProjectId(String projectId);
    File generateProjectFiles(String projectId);
}

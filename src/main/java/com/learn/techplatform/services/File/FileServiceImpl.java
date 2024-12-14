package com.learn.techplatform.services.File;

import com.learn.techplatform.common.enums.FileExploreType;
import com.learn.techplatform.common.enums.FileType;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.dto_modals.FileExploreDTO;
import com.learn.techplatform.entities.FileSystem;
import com.learn.techplatform.entities.Project;
import com.learn.techplatform.repositories.FileRepository;
import com.learn.techplatform.repositories.ProjectRepository;
import com.learn.techplatform.services.AbstractBaseService;
import com.learn.techplatform.services.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl extends AbstractBaseService<FileSystem, String> implements FileService {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    ProjectService projectService;

    public FileServiceImpl(JpaRepository<FileSystem, String> genericRepository) {
        super(genericRepository);
    }

    @Override
    public List<FileExploreDTO> getFileExploreDTOByProjectId(String projectId) {
        return fileRepository.getByProjectId(projectId);
    }

    @Override
    public File generateProjectFiles(String projectId) {
        Project project = projectService.getProjectByID(projectId);
        Validator.notNull(project, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        List<FileExploreDTO> fileExploreDTOS = this.getFileExploreDTOByProjectId(project.getId());
        Path projectPath = Paths.get("downloads", project.getSlug());
      try {
          if (Files.exists(projectPath)) {
              FileSystemUtils.deleteRecursively(projectPath);
          }
          Files.createDirectories(projectPath);
          Map<String, List<FileExploreDTO>> entriesByParentId = fileExploreDTOS.stream()
                  .filter(dto -> dto.getParentId() != null)
                  .collect(Collectors.groupingBy(FileExploreDTO::getParentId));

          List<FileExploreDTO> rootEntries = fileExploreDTOS.stream()
                  .filter(dto -> dto.getParentId() == null)
                  .collect(Collectors.toList());
          createFilesAndDirectories(projectPath, null, entriesByParentId,rootEntries);
          return projectPath.toFile();
      } catch (IOException e) {
          e.printStackTrace();
      }
        return null;
    }

    private void createFilesAndDirectories(Path currentPath, String parentId, Map<String, List<FileExploreDTO>> entriesByParentId, List<FileExploreDTO> rootEntries) throws IOException {
        List<FileExploreDTO> entries;
        if (parentId == null) {
            entries = rootEntries;
        } else {
            entries = entriesByParentId.get(parentId);
        }
        if (entries != null) {
            for (FileExploreDTO entry : entries) {
                if (entry.getType() == FileExploreType.DIRECTORY) {
                    Path dirPath = currentPath.resolve(entry.getName());
                    Files.createDirectories(dirPath);
                    createFilesAndDirectories(dirPath, entry.getId(), entriesByParentId, rootEntries);
                } else if (entry.getType() == FileExploreType.FILE) {
                    Path filePath = currentPath.resolve(entry.getName());
                    try (FileWriter writer = new FileWriter(filePath.toFile())) {
                        writer.write(entry.getContent());
                    }
                }
            }
        }
    }
}

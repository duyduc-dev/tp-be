package com.learn.techplatform.controllers;

import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.utils.ZipUtil;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.dto_modals.FileExploreDTO;
import com.learn.techplatform.dto_modals.ProjectDTO;
import com.learn.techplatform.entities.Project;
import com.learn.techplatform.services.File.FileService;
import com.learn.techplatform.services.Project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiPath.PROJECT_API)
public class ProjectController extends AbstractBaseController{

    @Autowired
    ProjectService projectService;

    @Autowired
    FileService fileService;

    @GetMapping(ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> getProjectById(@PathVariable("id") String id) {
        Project project = projectService.getProjectByID(id);
        Validator.notNull(project, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        List<FileExploreDTO> fileExploreDTO = fileService.getFileExploreDTOByProjectId(project.getId());
        return responseUtil.successResponse(new ProjectDTO(project, fileExploreDTO));
    }
    @GetMapping(ApiPath.SLUG)
    public ResponseEntity<RestAPIResponse<Object>> getProjectBySlug(@PathVariable("slug") String slug) {
        Project project = projectService.getProjectBySlug(slug);
        Validator.notNull(project, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        List<FileExploreDTO> fileExploreDTO = fileService.getFileExploreDTOByProjectId(project.getId());
        return responseUtil.successResponse(new ProjectDTO(project, fileExploreDTO));
    }

    @GetMapping(ApiPath.ID + ApiPath.DOWNLOAD)
    public ResponseEntity<Object> downloadProjectById(@PathVariable("id") String id) throws IOException {
        File projectDir = fileService.generateProjectFiles(id);
        Path zipPath = Paths.get(projectDir.getPath() + ".zip");
        ZipUtil.zipDirectory(projectDir.toPath(), zipPath);
        Resource resource = new FileSystemResource(zipPath.toFile());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zipPath.getFileName());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(zipPath.toFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping(ApiPath.GET_PAGE)
    @Operation(summary = "Get Project Pagination")
    ResponseEntity<RestAPIResponse<Object>> getPageProject(
        @RequestParam(name = "page_number", defaultValue = "1", required = false) int pageNumber,
        @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
        @RequestParam(name = "sort_type", defaultValue = "ASC", required = false) Sort.Direction sortType,
        @RequestParam(name = "search_key", defaultValue = "", required = false) String searchKey,
        @RequestParam(name = "is_template", required = false) boolean isTemplate,
        @RequestParam(name = "user_id", required = false) String userId
    )
    {
        return responseUtil.successResponse(projectService.getPageProject( pageNumber, pageSize, sortType,  searchKey, isTemplate, userId));
    }

//    @GetMapping(ApiPath.TEMPLATE)
//    @Operation(summary = "Get Project Pagination")
//    ResponseEntity<RestAPIResponse<Object>> getProjectTemplate()
//    {
//        return responseUtil.successResponse(projectService.getProjectTemplates());
//    }
}

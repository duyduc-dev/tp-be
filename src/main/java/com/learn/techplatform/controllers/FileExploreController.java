package com.learn.techplatform.controllers;

import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.controllers.models.request.FileExploreRequest;
import com.learn.techplatform.dto_modals.FileExploreDTO;
import com.learn.techplatform.entities.FileSystem;
import com.learn.techplatform.helper.FileExploreHelper;
import com.learn.techplatform.services.File.FileService;
import com.learn.techplatform.services.Project.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(ApiPath.FILE_EXPLORE_API)
public class FileExploreController extends AbstractBaseController{

    @Autowired
    ProjectService projectService;

    @Autowired
    FileService fileService;
    @Autowired
    FileExploreHelper fileExploreHelper;

    @PutMapping(ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> updateFileById(@RequestBody FileExploreRequest fileReq, @PathVariable String id) {
      FileExploreDTO fileExploreDTO = new FileExploreDTO(fileReq);
      FileSystem file = fileService.getById(id);
      Validator.notNull(file, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
      Validator.mustTrue(file.getSystemStatus() == SystemStatus.ACTIVE, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
      fileExploreHelper.editFileExplore(file, fileExploreDTO);
      fileService.save(file);
      return this.responseUtil.successResponse(file);
    }

    @PostMapping
    public ResponseEntity<RestAPIResponse<Object>> createFileById(@RequestBody FileExploreRequest fileReq) {
      FileExploreDTO fileExploreDTO = new FileExploreDTO(fileReq);
      FileSystem file = fileExploreDTO.newFile();
      fileService.save(file);
      return this.responseUtil.successResponse(file);
    }
}

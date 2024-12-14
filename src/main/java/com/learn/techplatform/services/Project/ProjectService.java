package com.learn.techplatform.services.Project;

import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.ProjectDTO;
import com.learn.techplatform.entities.Project;
import com.learn.techplatform.services.InterfaceBaseService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProjectService extends InterfaceBaseService<Project, String> {
    ProjectDTO getProjectDTO(String id);
    Project getProjectByID(String id);
    Project getProjectBySlug(String slug);
    List<Project> getProjectTemplates();
    PagingResponse getPageProject(int pageNumber, int pageSize, Sort.Direction sortType,String searchKey, boolean isTemplate, String userId);
}

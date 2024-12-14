package com.learn.techplatform.services.Project;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.ProjectDTO;
import com.learn.techplatform.entities.Project;
import com.learn.techplatform.repositories.ProjectRepository;
import com.learn.techplatform.services.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractBaseService<Project, String> implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public ProjectServiceImpl(JpaRepository<Project, String> genericRepository) {
        super(genericRepository);
    }

    @Override
    public ProjectDTO getProjectDTO(String id) {
        return this.projectRepository.getProjectDTOById(id);
    }

    @Override
    public Project getProjectByID(String id) {
        return this.projectRepository.getByIdAndSystemStatus(id, SystemStatus.ACTIVE);
    }

    @Override
    public Project getProjectBySlug(String slug) {
        return this.projectRepository.getBySlugAndSystemStatus(slug, SystemStatus.ACTIVE);
    }

    @Override
    public List<Project> getProjectTemplates() {
        return this.projectRepository.getProjectTemplate();
    }

    @Override
    public PagingResponse getPageProject(int pageNumber, int pageSize, Sort.Direction sortType,String searchKey, boolean isTemplate, String userId) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortType, "title"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        PagingResponse pagingResponse = new PagingResponse(projectRepository.getPageProject(userId,"%" + searchKey + "%",isTemplate, pageable));
        return pagingResponse;
    }
}

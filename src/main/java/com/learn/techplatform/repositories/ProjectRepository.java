package com.learn.techplatform.repositories;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.dto_modals.ProjectDTO;
import com.learn.techplatform.entities.Blog;
import com.learn.techplatform.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("""
        select new com.learn.techplatform.dto_modals.ProjectDTO(p)
        from Project p
        where p.id = :id and
            p.systemStatus = 'ACTIVE'
    """)
    ProjectDTO getProjectDTOById(@Param("id") String id);


    Project getByIdAndSystemStatus(String id, SystemStatus systemStatus);
    Project getBySlugAndSystemStatus(String slug, SystemStatus systemStatus);

    @Query("""
        select p
        from Project p
        where p.isTemplate = true and p.systemStatus = 'ACTIVE'
    """)
    List<Project> getProjectTemplate();

    @Query("""
        SELECT b
        FROM Project b
        WHERE b.title LIKE :search_key\s
            AND (b.userId = :user_id OR (b.isTemplate = :is_template AND b.isTemplate = true AND b.userId = ''))\s
            AND b.systemStatus = 'ACTIVE'
   \s""")
    Page<Project> getPageProject(@Param("user_id") String userId, @Param("search_key") String searchKey, @Param("is_template") boolean isTemplate, Pageable pageable);



}

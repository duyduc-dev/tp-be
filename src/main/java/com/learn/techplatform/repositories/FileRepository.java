package com.learn.techplatform.repositories;

import com.learn.techplatform.dto_modals.FileExploreDTO;
import com.learn.techplatform.entities.FileSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileSystem, String> {

    @Query("""
        SELECT new com.learn.techplatform.dto_modals.FileExploreDTO(f)
        FROM FileSystem f\s
        WHERE f.projectId = :id
            AND f.systemStatus = 'ACTIVE'
        ORDER BY f.depth
   \s""")
    List<FileExploreDTO> getByProjectId(@Param("id") String id);
}

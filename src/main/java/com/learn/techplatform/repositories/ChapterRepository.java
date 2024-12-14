package com.learn.techplatform.repositories;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.dto_modals.ChapterDTO;
import com.learn.techplatform.entities.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, String> {

    @Query("""
        SELECT NEW com.learn.techplatform.dto_modals.ChapterDTO(ch, course)
        FROM Chapter ch INNER JOIN Course course
        ON ch.courseId = course.id
        WHERE ch.systemStatus = 'ACTIVE' AND course.systemStatus = 'ACTIVE'
            AND (ch.title LIKE :search_key OR :search_key IS NULL)
            AND (course.id = :course_id OR :course_id IS NULL)
            AND (course.slug = :course_slug OR :course_slug IS NULL)
    """)                
    Page<ChapterDTO> getPage(
            @Param("search_key") String searchKey,
            @Param("course_id") String courseId,
            @Param("course_slug") String courseSlug,
            Pageable pageable
    );

    Chapter findFirstByPreviousChapterIdAndCourseIdAndSystemStatus(String previousChapterId, String courseId, SystemStatus systemStatus);

    List<Chapter> findByCourseIdAndSystemStatus(String courseId, SystemStatus status);
}

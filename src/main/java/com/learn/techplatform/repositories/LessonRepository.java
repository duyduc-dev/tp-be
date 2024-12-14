package com.learn.techplatform.repositories;

import com.learn.techplatform.dto_modals.LessonDTO;
import com.learn.techplatform.entities.Lesson;
import com.learn.techplatform.repositories.custom.CustomizedLessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String>, CustomizedLessonRepository {
    @Query("""
        SELECT l
        FROM Lesson l
            JOIN Chapter ch ON ch.id = l.chapterId
            JOIN Course c ON c.id = ch.courseId
        WHERE l.systemStatus = 'ACTIVE'
            AND c.systemStatus = 'ACTIVE'
            AND ch.systemStatus = 'ACTIVE'
            AND (l.title LIKE :search_key OR :search_key IS NULL)
            AND (:course_id IS NULL OR c.id = :course_id)
            AND (:chapter_id IS NULL OR ch.id = :chapter_id)
    """)
    Page<Lesson> getPageLesson(
            @Param("search_key") String searchKey,
            @Param("course_id") String courseId,
            @Param("chapter_id") String chapterId,
            Pageable pageable
    );

    @Query("""
        select new com.learn.techplatform.dto_modals.LessonDTO(l)
        from Lesson l
        where l.id = :id and l.systemStatus = 'ACTIVE'
    """)
    LessonDTO getDTOById(@Param("id") String id);

    @Query("""
        SELECT l
        FROM Course c LEFT JOIN Chapter ch
        ON c.id = ch.courseId\s
        LEFT JOIN Lesson l
        ON l.chapterId = ch.id
        WHERE c.id = :course_id
            AND c.systemStatus = 'ACTIVE'
            AND l.systemStatus = 'ACTIVE'
            AND ch.systemStatus = 'ACTIVE'
   \s""")
    List<Lesson> findByCourseId(@Param("course_id") String courseId);

    Lesson findByPreviousLessonId(String previousLessonId);
}

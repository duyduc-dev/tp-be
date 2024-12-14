package com.learn.techplatform.repositories;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.dto_modals.ChapterDTO;
import com.learn.techplatform.dto_modals.CourseDTO;
import com.learn.techplatform.dto_modals.LessonDTO;
import com.learn.techplatform.dto_modals.MetricDTO;
import com.learn.techplatform.entities.Course;
import com.learn.techplatform.repositories.custom.CustomizedCourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Course findByIdAndSystemStatus(String id, SystemStatus systemStatus);
    boolean existsByTitle(String title);
    Course getByCodeAndSystemStatus(String code, SystemStatus systemStatus);


    @Query("""
        SELECT new com.learn.techplatform.dto_modals.CourseDTO(c)
        FROM Course c 
        WHERE (c.title LIKE :search_key or CAST(c.price as String) LIKE :search_key or c.code like :search_key)
               AND c.systemStatus = 'ACTIVE'
    """)
    Page<CourseDTO> getPageCourse(@Param("search_key") String searchKey, Pageable pageable);


    @Query("""
        select new com.learn.techplatform.dto_modals.CourseDTO(c)
        from Course c
        where c.systemStatus = 'ACTIVE'
    """)
    List<CourseDTO> getCourseDTOList();

    @Query("""
        SELECT new com.learn.techplatform.dto_modals.MetricDTO(c.title, COUNT(uc.id))
        FROM Course c
            JOIN UserCourse uc ON c.id = uc.courseId AND uc.systemStatus = 'ACTIVE'
        WHERE c.systemStatus = 'ACTIVE'
        GROUP BY c.id
    """)
    List<MetricDTO> getMetrics();

    @Query("""
        SELECT
            new com.learn.techplatform.dto_modals.ChapterDTO(ch, COUNT(l.id))
        FROM Course c
            JOIN Chapter ch ON c.id = ch.courseId AND ch.systemStatus = 'ACTIVE'
            LEFT JOIN Lesson l ON ch.id = l.chapterId AND l.systemStatus = 'ACTIVE'
        WHERE 
            c.slug = :slug
            AND c.systemStatus = 'ACTIVE'
        GROUP BY c.id, ch.id
    """)
    List<ChapterDTO> getCourseChapters(@Param("slug") String slug);

    @Query("""
        SELECT
            new com.learn.techplatform.dto_modals.LessonDTO(l)
        FROM Course c
            JOIN Chapter ch ON c.id = ch.courseId AND ch.systemStatus = 'ACTIVE' AND ch.id = :chapter_id
            JOIN Lesson l ON ch.id = l.chapterId AND l.systemStatus = 'ACTIVE'
        WHERE 
            c.slug = :slug
            AND c.systemStatus = 'ACTIVE'
    """)
    List<LessonDTO> getCourseLessons(@Param("slug") String slug, @Param("chapter_id") String chapterId );

    @Query("""
        SELECT new com.learn.techplatform.dto_modals.CourseDTO(c)
        FROM\s
              Course c
              JOIN Chapter ch on c.id = ch.courseId AND ch.systemStatus = 'ACTIVE'
              JOIN Lesson l ON ch.id = l.chapterId AND l.id = :lesson_id AND l.systemStatus = 'ACTIVE'
        WHERE c.systemStatus = 'ACTIVE'
    \s""")
    CourseDTO getCourseByLessonId(@Param("lesson_id") String lessonId);


    @Query(value = """
       SELECT
           new com.learn.techplatform.dto_modals.CourseDTO(
               c,
               CASE WHEN uc.userId IS NOT NULL THEN true ELSE false END,
               uc.currentLessonId
           )
       FROM
           Course c
       LEFT JOIN (
       		SELECT u.id as userId, inner_uc.currentLessonId as currentLessonId, inner_uc.courseId as courseId
       		FROM UserCourse inner_uc
       		JOIN User u
       			ON u.id = inner_uc.userId
           	WHERE
       			u.id = :user_id
       			AND u.systemStatus = 'ACTIVE'
       			AND inner_uc.systemStatus = 'ACTIVE'
       ) uc
       	ON uc.courseId = c.id AND c.systemStatus = 'ACTIVE'
       WHERE
           c.slug = :slug
           AND c.systemStatus = 'ACTIVE'
    """)
    CourseDTO getBySlug(@Param("slug") String slug,@Param("user_id")  String userId);

}

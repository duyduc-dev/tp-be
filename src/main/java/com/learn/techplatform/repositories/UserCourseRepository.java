package com.learn.techplatform.repositories;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.dto_modals.UserCourseDTO;
import com.learn.techplatform.entities.UserCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, String> {

    Optional<UserCourse> findByCourseIdAndUserIdAndSystemStatus(String courseId, String userId, SystemStatus systemStatus);

    @Query("""
        SELECT new com.learn.techplatform.dto_modals.UserCourseDTO(uc, course)
        FROM UserCourse uc LEFT JOIN Course course
        ON course.id = uc.courseId
        WHERE uc.systemStatus = 'ACTIVE'\s
            AND course.title LIKE :search_key
            AND uc.userId = :user_id
   \s""")
    Page<UserCourseDTO> getPage(
            @Param("search_key") String searchKey,
            @Param("user_id") String userId,
            Pageable pageable
    );
}

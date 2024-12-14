package com.learn.techplatform.repositories.custom;

import com.learn.techplatform.dto_modals.CourseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomizedCourseRepositoryImpl implements CustomizedCourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CourseDTO getBySlug(String slug, String userId) {
        String isUserRegisteredSQL = "false as isUserRegistered";
        String currentLessonId = "null as currentLessonId";
        String $sql = """
            SELECT new com.learn.techplatform.dto_modals.CourseDTO(
                c,
                %s,
                %s
            )
            FROM Course c
        """;
        if(userId != null) {
            currentLessonId = "uc.currentLessonId";
            isUserRegisteredSQL = "(CASE WHEN uc.userId IS NOT NULL THEN true ELSE false END) as isUserRegistered";
            $sql += """
                    LEFT JOIN (
                        SELECT u.id as userId, inner_uc.currentLessonId, inner_uc.courseId
                        FROM UserCourse inner_uc
                        JOIN User u
                            ON u.id = inner_uc.userId
                            AND u.id = :user_id
                            AND u.systemStatus = 'ACTIVE'
                            AND inner_uc.systemStatus = 'ACTIVE'
                    ) AS uc
                    ON uc.courseId = c.id AND c.systemStatus = 'ACTIVE'
                    """;
        }
        $sql += "WHERE c.slug = :slug AND c.systemStatus = 'ACTIVE'";
        String mySQL = String.format($sql, isUserRegisteredSQL, currentLessonId);
        TypedQuery<CourseDTO> query = entityManager.createQuery(mySQL, CourseDTO.class);
        if(userId != null) query.setParameter("user_id", userId);
        query.setParameter("slug", slug);
        return query.getSingleResult();
    }
}

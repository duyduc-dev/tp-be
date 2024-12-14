package com.learn.techplatform.repositories.custom;

import com.learn.techplatform.common.constants.Constant;
import com.learn.techplatform.dto_modals.LessonDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CustomizedLessonRepositoryImpl implements CustomizedLessonRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<LessonDTO> getLessonDtoPaging(
            String searchKey,
            String courseId,
            String chapterId,
            String sortField,
            boolean ascSort,
            Pageable pageable
    ) {
        String sql = """
               SELECT new com.learn.techplatform.dto_modals.LessonDTO(l)
               FROM Lesson l
                    LEFT JOIN Chapter ch ON ch.id = l.chapterId AND ch.systemStatus = 'ACTIVE'
                    LEFT JOIN Course c ON c.id = ch.courseId AND c.systemStatus = 'ACTIVE'
               WHERE l.systemStatus = 'ACTIVE'
                    AND (l.title LIKE :search_key OR :search_key IS NULL)
                    AND (ch.id = :chapter_id OR :chapter_id IS NULL)
                    AND (c.id = :course_id OR :course_id IS NULL)
                    ORDER BY %s %s
        """;
        if(sortField == null) {
            sortField = "l." + Constant.CREATED_DATE;
        } else
            switch (sortField) {
                case "title":
                    sortField = "l.title";
                    break;
                case "slug":
                    sortField = "l.slug";
                    break;
                default:
                    sortField = "l." + Constant.CREATED_DATE;
            }
        String mySQL = String.format(sql, sortField, ascSort ? "ASC" : "DESC");
        TypedQuery<LessonDTO> query = entityManager.createQuery(mySQL, LessonDTO.class);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        query.setParameter("search_key", searchKey != null && !searchKey.isBlank() ? "%" + searchKey + "%" : null);
        query.setParameter("chapter_id", chapterId != null && !chapterId.isBlank() ? chapterId : null);
        query.setParameter("course_id", courseId != null && !courseId.isBlank() ? courseId : null);
        List<LessonDTO> lessonDTOList = query.getResultList();
        Long total = totalLessonDtoPaging(searchKey, courseId, chapterId);
        return new PageImpl<>(lessonDTOList, pageable, total);
    }

    private Long totalLessonDtoPaging(
            String searchKey,
            String courseId,
            String chapterId
    ) {
        String sql = """
               SELECT COUNT(l.id)
               FROM Lesson l
                    LEFT JOIN Chapter ch ON ch.id = l.chapterId AND ch.systemStatus = 'ACTIVE'
                    LEFT JOIN Course c ON c.id = ch.courseId AND c.systemStatus = 'ACTIVE'
               WHERE l.systemStatus = 'ACTIVE'
                    AND (l.title LIKE :search_key OR :search_key IS NULL)
                    AND (ch.id = :chapter_id OR :chapter_id IS NULL)
                    AND (c.id = :course_id OR :course_id IS NULL)
        """;
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        query.setParameter("search_key", searchKey != null && !searchKey.isBlank() ? "%" + searchKey + "%" : null);
        query.setParameter("chapter_id", chapterId != null && !chapterId.isBlank() ? chapterId : null);
        query.setParameter("course_id", courseId != null && !courseId.isBlank() ? courseId : null);
        return query.getSingleResult();
    }
}

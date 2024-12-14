package com.learn.techplatform.repositories.custom;

import com.learn.techplatform.dto_modals.LessonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomizedLessonRepository {
    Page<LessonDTO> getLessonDtoPaging(
            String searchKey,
            String courseId,
            String chapterId,
            String sortField,
            boolean ascSort,
            Pageable pageable
    );
}

package com.learn.techplatform.services.Lesson;

import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.LessonDTO;
import com.learn.techplatform.entities.Lesson;
import com.learn.techplatform.entities.LessonQuestion;
import com.learn.techplatform.services.InterfaceBaseService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonService extends InterfaceBaseService<Lesson, String> {

    PagingResponse getPageLesson(
            int pageNumber,
            int pageSize,
            String sortField,
            boolean isAscSort,
            String searchKey,
            String courseId,
            String chapterId
    );
    LessonDTO getLessonById(String id);
    LessonDTO createNewLesson(LessonDTO lessonDTO);

    List<LessonDTO> getAllLessonsByCourseId(String courseId);

    String unlockNextLesson(String userId, String currentLessonId);
}

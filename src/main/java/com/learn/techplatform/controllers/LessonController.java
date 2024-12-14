package com.learn.techplatform.controllers;

import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.controllers.models.request.NewLessonRequest;
import com.learn.techplatform.dto_modals.LessonDTO;
import com.learn.techplatform.services.Lesson.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(ApiPath.LESSON_API)
public class LessonController extends AbstractBaseController{
    @Autowired
    LessonService lessonService;

    @GetMapping(ApiPath.GET_PAGE)
    @Operation(summary = "Get Lesson Pagination")
    ResponseEntity<RestAPIResponse<Object>> getPageLesson(@RequestParam(name = "page_number", defaultValue = "1", required = false) int pageNumber,
                                                          @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
                                                          @RequestParam(name = "is_asc_sort", defaultValue = "true", required = false) boolean isAscSort,
                                                          @RequestParam(name = "sort_field", required = false) String sortField,
                                                          @RequestParam(name = "search_key", defaultValue = "", required = false) String searchKey,
                                                          @RequestParam(name = "course_id", defaultValue = "", required = false) String courseId,
                                                          @RequestParam(name = "chapter_id", defaultValue = "", required = false) String chapterId
    )
    {
        return responseUtil.successResponse(lessonService.getPageLesson(pageNumber, pageSize, sortField, isAscSort, searchKey, courseId, chapterId));
    }

    @GetMapping()
    @Operation(summary = "Get All Lesson")
    ResponseEntity<RestAPIResponse<Object>> getAllLesson() {
        return responseUtil.successResponse(lessonService.getAll());
    }

    @GetMapping(ApiPath.ID)
    @Operation(summary = "Get Video Lesson By ID")
    ResponseEntity<RestAPIResponse<Object>> getVideoLessonById(@PathVariable("id") String id) {
        return responseUtil.successResponse(lessonService.getLessonById(id));
    }

    @PostMapping
    @Operation(summary = "Create new video lesson")
    ResponseEntity<RestAPIResponse<Object>> newLesson(@Valid @RequestBody NewLessonRequest newLessonRequest) {
        return responseUtil.successResponse(lessonService.createNewLesson(new LessonDTO(newLessonRequest)));
    }
}

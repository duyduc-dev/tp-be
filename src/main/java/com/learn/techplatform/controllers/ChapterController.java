package com.learn.techplatform.controllers;

import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.controllers.models.request.NewChapterRequest;
import com.learn.techplatform.dto_modals.ChapterDTO;
import com.learn.techplatform.entities.Course;
import com.learn.techplatform.security.AuthorizeValidator;
import com.learn.techplatform.services.Chapter.ChapterService;
import com.learn.techplatform.services.Course.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(ApiPath.CHAPTER_API)
public class ChapterController extends AbstractBaseController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    CourseService courseService;

    @AuthorizeValidator
    @GetMapping(ApiPath.GET_PAGE)
    ResponseEntity<RestAPIResponse<Object>> getAllChapter(
            @RequestParam(name = "page_number", defaultValue = "1", required = false) int pageNumber,
            @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
            @RequestParam(name = "sort_type", defaultValue = "ASC", required = false) Sort.Direction sortType,
            @RequestParam(name = "search_key", defaultValue = "", required = false) String searchKey,
            @RequestParam(name = "course_id", required = false) String courseId,
            @RequestParam(name = "course_slug", required = false) String courseSlug
    ) {
        return responseUtil.successResponse(chapterService.getPage(pageNumber, pageSize, sortType,searchKey, courseId, courseSlug));
    }

    @AuthorizeValidator
    @PostMapping
    ResponseEntity<RestAPIResponse<Object>> newChapter(@RequestBody @Valid NewChapterRequest newChapterRequest) {
        ChapterDTO chapterDTO = new ChapterDTO(newChapterRequest);
        Course course = courseService.getByIdAndSystemStatus(chapterDTO.getCourseId(), SystemStatus.ACTIVE);
        Validator.notNull(course, RestAPIStatus.NOT_FOUND, RestStatusMessage.COURSE_NOT_FOUND);
        return this.responseUtil.successResponse(chapterService.createChapter(chapterDTO));
    }

}

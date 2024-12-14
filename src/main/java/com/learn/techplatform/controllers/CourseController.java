package com.learn.techplatform.controllers;

import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.controllers.models.request.EditCourseRequest;
import com.learn.techplatform.dto_modals.CourseDTO;
import com.learn.techplatform.security.AuthSession;
import com.learn.techplatform.security.AuthUser;
import com.learn.techplatform.security.AuthorizeValidator;
import com.learn.techplatform.services.Course.CourseService;
import com.learn.techplatform.services.Lesson.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(ApiPath.COURSE_API)
public class CourseController extends AbstractBaseController {

    private final CourseService courseService;
    private final LessonService lessonService;

    public CourseController(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @GetMapping
    @Operation(summary = "Get All Course")
    ResponseEntity<RestAPIResponse<Object>> getAllCourse()
    {
        List<CourseDTO> courseDTOS = courseService.getCourseDTO()
                .stream()
                .peek(item -> item.setThumbnailUrl(this.getCloudinaryUrl(item.getThumbnailUrl())))
                .toList();
        return responseUtil.successResponse(courseDTOS);
    }

    @GetMapping(ApiPath.GET_PAGE)
    @Operation(summary = "Get Course Pagination")
    ResponseEntity<RestAPIResponse<Object>> getPageCourse(@RequestParam(name = "page_number", defaultValue = "1", required = false) int pageNumber,
                                         @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
                                         @RequestParam(name = "sort_type", defaultValue = "ASC", required = false) Sort.Direction sortType,
                                         @RequestParam(name = "sort_type_date", defaultValue = "ASC", required = false) Sort.Direction sortTypeDate,
                                         @RequestParam(name = "search_key", defaultValue = "", required = false) String searchKey)
    {
        return responseUtil.successResponse(courseService.getPageCourse(pageNumber, pageSize, sortType, sortTypeDate, searchKey));
    }

    @AuthorizeValidator
    @PostMapping(ApiPath.ADD)
    @Operation(summary = "Create new course")
    ResponseEntity<RestAPIResponse<Object>> createCourse(@RequestBody @Valid EditCourseRequest editCourseRequest) {
        CourseDTO courseDTO = new CourseDTO(editCourseRequest);
        courseService.createCourse(courseDTO);
        return responseUtil.successResponse("OK!");
    }

    @PostMapping(ApiPath.REGISTER  + ApiPath.ID)
    @Operation(summary = "Register the course")
    ResponseEntity<RestAPIResponse<Object>> registerCourse(@PathVariable("id") String id, @AuthSession AuthUser authSession) {
        this.courseService.registerCourse(id, authSession.getId());
        return responseUtil.successResponse("ok");
    }

    @GetMapping(ApiPath.PAYMENT_COURSE + ApiPath.ID)
    @Operation(summary = "get payment the course")
    ResponseEntity<RestAPIResponse<Object>> getPaymentCourse(@PathVariable("id") String id, @AuthSession AuthUser authSession) {
        return responseUtil.successResponse(this.courseService.getPaymentCourseInfo(authSession.getId(), id, appValueConfigure));
    }

    @GetMapping(ApiPath.SLUG)
    @Operation(summary = "Get course by slug")
    ResponseEntity<RestAPIResponse<Object>> getCourseBySlug(
            @PathVariable("slug") String slug,
            @AuthSession(required = false) AuthUser authSession
    ) {
        CourseDTO course = this.courseService.getCourseBySlug(slug, appValueConfigure, authSession != null ? authSession.getId() : null);
        course.setThumbnailUrl(this.getCloudinaryUrl(course.getThumbnailUrl()));
        return responseUtil.successResponse(course);
    }

    @AuthorizeValidator
    @GetMapping(ApiPath.METRICS)
    ResponseEntity<RestAPIResponse<Object>> getCourseDetail() {
        return responseUtil.successResponse(
               this.courseService.getCourseMetrics()
        );
    }


    @GetMapping("/{slug}/chapters")
    ResponseEntity<RestAPIResponse<Object>> getCourseChapters(@PathVariable("slug") String slug) {
        return responseUtil.successResponse(
                this.courseService.getCourseChapters(slug)
        );
    }


    @GetMapping("/{slug}/lessons")
    ResponseEntity<RestAPIResponse<Object>> getCourseChapters(
            @PathVariable("slug") String slug,
            @RequestParam(name = "chapter_id") String chapterId
    ) {
        return responseUtil.successResponse(
                this.courseService.getCourseLessons(slug, chapterId)
        );
    }

    @PostMapping(ApiPath.NEXT_LESSON)
    ResponseEntity<RestAPIResponse<Object>> unlockNextLesson(
            @AuthSession AuthUser authUser,
            @RequestParam(name = "current_lesson_id") String lessonId
    ) {
        String nextLesson = this.lessonService.unlockNextLesson(authUser.getId(), lessonId);
        Map<String, String> response = new HashMap<>();
        response.put("nextLessonId", nextLesson);
        return responseUtil.successResponse(response);
    }

    @AuthorizeValidator
    @DeleteMapping(ApiPath.ID)
    ResponseEntity<RestAPIResponse<Object>> deleteCourse(
            @PathVariable("id") String courseId
    ) {
        this.courseService.inactiveCourseById(courseId);
        return responseUtil.successResponse("ok");
    }
}

package com.learn.techplatform.services.Course;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.utils.AppValueConfigure;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.controllers.models.response.PaymentCourseResponse;
import com.learn.techplatform.dto_modals.ChapterDTO;
import com.learn.techplatform.dto_modals.CourseDTO;
import com.learn.techplatform.dto_modals.LessonDTO;
import com.learn.techplatform.dto_modals.MetricDTO;
import com.learn.techplatform.entities.Course;
import com.learn.techplatform.services.InterfaceBaseService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CourseService  extends InterfaceBaseService<Course, String> {
    void createCourse(CourseDTO courseDTO);
    void registerCourse(String courseId, String userId);
    PagingResponse getPageCourse(int pageNumber, int pageSize, Sort.Direction sortType, Sort.Direction sortTypeDate, String searchKey);
    List<CourseDTO> getCourseDTO();
    PaymentCourseResponse getPaymentCourseInfo(String token, String courseId, AppValueConfigure appValueConfigure);
    Course getCourseByCode(String courseCode);
    Course getByIdAndSystemStatus(String Id, SystemStatus status);
    CourseDTO getCourseBySlug(String slug, AppValueConfigure appValueConfigure, String userId);
    List<MetricDTO> getCourseMetrics();
    List<ChapterDTO> getCourseChapters(String slug);
    List<LessonDTO> getCourseLessons(String slug, String chapterId);
    CourseDTO getCourseByLessonId(String lessonId);

    void inactiveCourseById(String courseId);
}

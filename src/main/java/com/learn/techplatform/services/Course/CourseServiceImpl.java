package com.learn.techplatform.services.Course;

import com.learn.techplatform.common.enums.*;
import com.learn.techplatform.common.exceptions.ApplicationException;
import com.learn.techplatform.common.restfullApi.HttpRequestUtil;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.utils.*;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.controllers.models.response.PaymentCourseResponse;
import com.learn.techplatform.dto_modals.*;
import com.learn.techplatform.entities.*;
import com.learn.techplatform.helper.ChapterHelper;
import com.learn.techplatform.helper.UserCourseHelper;
import com.learn.techplatform.repositories.CourseRepository;
import com.learn.techplatform.security.AuthHelper;
import com.learn.techplatform.services.AbstractBaseService;
import com.learn.techplatform.services.Chapter.ChapterService;
import com.learn.techplatform.services.Lesson.LessonService;
import com.learn.techplatform.services.Transaction.TransactionService;
import com.learn.techplatform.services.User.UserService;
import com.learn.techplatform.services.UserCourse.UserCourseService;
import com.learn.techplatform.vietqr_bank.modals.GenerateVietQrRequest;
import com.learn.techplatform.vietqr_bank.modals.VietQrResponse;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl extends AbstractBaseService<Course, String> implements CourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    private final UserService userService;

    private final UserCourseService userCourseService;
    private final TransactionService transactionService;
    private final ChapterService chapterService;
    private final LessonService lessonService;
    private final AuthHelper authHelper;

    private final UserCourseHelper userCourseHelper;
    private final ChapterHelper chapterHelper;
    private final AppValueConfigure appValueConfigure;


    private static final HttpRequestUtil httpRequestUtil = new HttpRequestUtil();

    public CourseServiceImpl(JpaRepository<Course, String> genericRepository, CourseRepository courseRepository, UserService userService, UserCourseService userCourseService, TransactionService transactionService, ChapterService chapterService, LessonService lessonService, AuthHelper authHelper, UserCourseHelper userCourseHelper, ChapterHelper chapterHelper, AppValueConfigure appValueConfigure) {
        super(genericRepository);
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.userCourseService = userCourseService;
        this.transactionService = transactionService;
        this.chapterService = chapterService;
        this.lessonService = lessonService;
        this.authHelper = authHelper;
        this.userCourseHelper = userCourseHelper;
        this.chapterHelper = chapterHelper;
        this.appValueConfigure = appValueConfigure;
    }


    @Override
    public PagingResponse getPageCourse(int pageNumber, int pageSize, Sort.Direction sortType, Sort.Direction sortTypeDate, String searchKey) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortType, "title"));
        orders.add(new Sort.Order(sortTypeDate, "updatedDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<CourseDTO> data = courseRepository.getPageCourse("%" + searchKey + "%",  pageable);
        data.forEach(courseDTO -> courseDTO.setThumbnailUrl(AppUtil.getUrlFile(appValueConfigure, courseDTO.getThumbnailUrl())));
        return new PagingResponse(data);
    }

    @Override
    public void createCourse(CourseDTO courseDTO) {
        boolean isCourseExist = courseRepository.existsByTitle(courseDTO.getTitle());
        Validator.mustTrue(!isCourseExist, RestAPIStatus.EXISTED, RestStatusMessage.COURSE_ALREADY_EXISTED);

        Validator.notNullAndNotEmpty(courseDTO.getTitle(), RestAPIStatus.BAD_REQUEST, RestStatusMessage.INVALID_CONTENT_FORMAT);
        Validator.notNullAndNotEmpty(courseDTO.getDescription(), RestAPIStatus.BAD_REQUEST, RestStatusMessage.INVALID_CONTENT_FORMAT);
        Validator.notNull(courseDTO.getPrice(), RestAPIStatus.BAD_REQUEST, RestStatusMessage.INVALID_CONTENT_FORMAT);
        Validator.notNullAndNotEmpty(courseDTO.getContent(), RestAPIStatus.BAD_REQUEST, RestStatusMessage.INVALID_CONTENT_FORMAT);

        Course course = Course.builder()
                .id(UniqueID.getUUID())
                .title(courseDTO.getTitle())
                .description(courseDTO.getDescription())
                .price(courseDTO.getPrice())
                .content(courseDTO.getContent())
                .slug(StringUtils.slugify(courseDTO.getTitle()))
                .courseType(CourseType.NONE)
                .thumbnailUrl(courseDTO.getThumbnailUrl())
                .discount(0)
                .viewed(0)
                .code("TP"+UniqueID.generateKey(5).toUpperCase())
                .systemStatus(SystemStatus.ACTIVE)
                .build();

        this.save(course);
    }


    @Override
    public List<CourseDTO> getCourseDTO() {
        return this.courseRepository.getCourseDTOList();
    }

    @Override
    public void registerCourse(String courseId, String userId) {
        Course course = this.courseRepository.findByIdAndSystemStatus(courseId, SystemStatus.ACTIVE);
        Validator.notNull(course, RestAPIStatus.NOT_FOUND, RestStatusMessage.COURSE_NOT_FOUND);
        Validator.mustTrue(course.getPrice() <= 0, RestAPIStatus.FORBIDDEN, RestStatusMessage.FORBIDDEN_ACCESS_DENIED);
        UserCourse userCourse = userCourseService.getByUserIdAndCourseId(userId, courseId, false);
        Validator.mustNull(userCourse, RestAPIStatus.EXISTED, RestStatusMessage.COURSE_ALREADY_REGISTERED);
        UserCourse newUserCourse = userCourseHelper.createUserCourse(courseId, userId);
        Transaction transaction = Transaction.builder()
                .note(TransactionType.BUY_COURSE.name())
                .transactionType(TransactionType.BUY_COURSE)
                .referenceId(courseId)
                .amount(course.getPrice())
                .userId(userId)
                .systemStatus(SystemStatus.ACTIVE)
                .status(TransactionStatus.SUCCEED)
                .id(UniqueID.getUUID())
                .build();
        transactionService.save(transaction);
        userCourseService.save(newUserCourse);
    }

    @Override
    public PaymentCourseResponse getPaymentCourseInfo(String userId, String courseId,  AppValueConfigure appValueConfigure) {
        UserDTO user = userService.getAuthInfo(userId);
        Validator.notNull(user, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        Course course = this.courseRepository.findByIdAndSystemStatus(courseId, SystemStatus.ACTIVE);
        Validator.notNull(course, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);

        try {
            GenerateVietQrRequest generateVietQrRequest = GenerateVietQrRequest.builder()
                    .accountName(appValueConfigure.bankAccountName)
                    .accountNo(appValueConfigure.bankAccountNo)
                    .acqId(appValueConfigure.bankAcqId)
                    .amount(course.getPrice() )
                    .template(VietQrTemplate.TP_QR)
                    .addInfo("KH "+course.getCode()+" U "+user.getUsername()+ " E")
                    .build();
            //        Header

            Map<String, String> headers = new HashMap<>();
            headers.put("x-client-id", appValueConfigure.vietQrClientId);
            headers.put("x-api-key", appValueConfigure.getVietQrApikey);
            headers.put("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = httpRequestUtil.postRequest(
                    new StringEntity(generateVietQrRequest.toJSONObject().toString(), StandardCharsets.UTF_8),
                    appValueConfigure.vietQrApiUrl,
                    ContentType.APPLICATION_JSON,
                    headers
            );
            String bodyRes = getResponseBody(response);
            JSONObject dataObj = new JSONObject(bodyRes);

            VietQrResponse vietQrResponse = new VietQrResponse(dataObj);
            return PaymentCourseResponse.builder()
                    .qrCode(vietQrResponse.getData().getQrCode())
                    .qrDataURL(vietQrResponse.getData().getQrDataURL())
                    .accountName(generateVietQrRequest.getAccountName())
                    .accountNo(generateVietQrRequest.getAccountNo())
                    .addInfo(generateVietQrRequest.getAddInfo())
                    .amount(generateVietQrRequest.getAmount())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(RestAPIStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Course getCourseByCode(String courseCode) {
        return this.courseRepository.getByCodeAndSystemStatus(courseCode, SystemStatus.ACTIVE);
    }

    @Override
    public Course getByIdAndSystemStatus(String Id, SystemStatus status) {
        return courseRepository.findByIdAndSystemStatus(Id, status);
    }

    @Override
    public CourseDTO getCourseBySlug(String slug, AppValueConfigure appValueConfigure, String userId) {
        CourseDTO course = courseRepository.getBySlug(slug, userId);
        Validator.notNull(course, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        return course;
    }

    @Override
    public List<MetricDTO> getCourseMetrics() {
        return this.courseRepository.getMetrics();
    }

    @Override
    public List<ChapterDTO> getCourseChapters(String slug) {
        List<ChapterDTO> chapters = this.courseRepository.getCourseChapters(slug);
        Map<String, ChapterDTO> chapterMap = chapters.stream()
                .collect(Collectors.toMap(ChapterDTO::getPreviousChapterId, chapter -> chapter));
        List<ChapterDTO> sortedChapters = new ArrayList<>();
        ChapterDTO start = chapterMap.get(null);
        int position = 0;
        while (start != null) {
            start.setPosition(position++);
            sortedChapters.add(start);
            start = chapterMap.get(start.getId());
        }
        return sortedChapters;
    }

    @Override
    public List<LessonDTO> getCourseLessons(String slug, String chapterId) {
        List<LessonDTO> lessonDTOS = this.courseRepository.getCourseLessons(slug, chapterId);
        Map<String, LessonDTO> chapterMap = lessonDTOS.stream()
                .collect(Collectors.toMap(LessonDTO::getPreviousLessonId, lessonDTO -> lessonDTO));
        List<LessonDTO> sortedChapters = new ArrayList<>();
        LessonDTO start = chapterMap.get(null);
        int position = 0;
        while (start != null) {
            start.setOrder(position++);
            LessonDTO nextLesson = chapterMap.get(start.getId());
            start.setNextLessonId(nextLesson != null ? nextLesson.getId() : null);
            sortedChapters.add(start);
            start = nextLesson;
        }
        return sortedChapters;
    }

    @Override
    public CourseDTO getCourseByLessonId(String lessonId) {
        return this.courseRepository.getCourseByLessonId(lessonId);
    }

    @Override
    public void inactiveCourseById(String courseId) {
        Course course = this.courseRepository.findById(courseId).orElse(null);
        Validator.notNull(course, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        course.setSystemStatus(SystemStatus.INACTIVE);
        this.courseRepository.saveAndFlush(course);
    }

    private static String getResponseBody(HttpResponse response) {
        if (response == null) {
            log.error("response is null");
            throw new ApplicationException(RestAPIStatus.INTERNAL_SERVER_ERROR);
        }
        String responseBody;
        try {
            responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(RestAPIStatus.INTERNAL_SERVER_ERROR);
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new ApplicationException(RestAPIStatus.VIETQR_API_ERROR, responseBody);
        }

        return responseBody;
    }
}

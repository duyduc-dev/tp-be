package com.learn.techplatform.casso_webhook_recerver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.techplatform.casso_webhook_recerver.modals.CassoErrorStatus;
import com.learn.techplatform.casso_webhook_recerver.modals.CassoKeys;
import com.learn.techplatform.casso_webhook_recerver.modals.CassoResponse;
import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.enums.OrderHistoryStatus;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;
import com.learn.techplatform.common.restfullApi.ResponseUtil;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.common.utils.UniqueID;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.controllers.AbstractBaseController;
import com.learn.techplatform.dto_modals.UserDTO;
import com.learn.techplatform.entities.*;
import com.learn.techplatform.fcm.FirebaseCloudMessageService;
import com.learn.techplatform.firebase.FirebaseService;
import com.learn.techplatform.firebase.modals.CoursePaymentNotification;
import com.learn.techplatform.firebase.modals.PushNotification;
import com.learn.techplatform.firebase.modals.PushNotificationType;
import com.learn.techplatform.services.Course.CourseService;
import com.learn.techplatform.services.Transaction.TransactionService;
import com.learn.techplatform.services.User.UserService;
import com.learn.techplatform.services.UserCourse.UserCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApiPath.CASSO_WEBHOOK_HANDLER)
public class CassoWebhookController {
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;

    @Autowired
    UserCourseService userCourseService;
    @Autowired
    TransactionService transactionService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FirebaseService firebaseService;

    @PostMapping
    ResponseEntity<RestAPIResponse<Object>> cassoWebhookHandler(@RequestBody Object data) {
        try {
            log.info("Casso Webhook Handler {}",data);

            UserCourse userCourse = UserCourse.builder()
                    .id(UniqueID.getUUID())
                    .systemStatus(SystemStatus.ACTIVE)
                    .build();
            Transaction transaction = Transaction.builder()
                    .id(UniqueID.getUUID())
                    .systemStatus(SystemStatus.ACTIVE)
                    .build();
            User user = null;
            Course course = null;
            CassoResponse cassoResponse = objectMapper.convertValue(data, CassoResponse.class);
            try {

                if(cassoResponse.error != CassoErrorStatus.SUCCESS.getStatusCode()) {
                    log.error("Error occurred {}", cassoResponse.getData());
                    return responseUtil.successResponse("ok");
                }
                CassoKeys cassoKeys = getKeyData(cassoResponse);
                course = courseService.getCourseByCode(cassoKeys.getCourseCode());
                user = userService.getByUsername(cassoKeys.getUsername());
                if(user == null || course == null) {
                    log.error("User or Course is null, user {}, course {}", user, course);
                    return responseUtil.successResponse("ok");
                }
                UserCourse userCourseExist = userCourseService.getByUserIdAndCourseId(user.getId(), course.getId(), false);
                if(userCourseExist != null) {
                    log.error("Course '{}' already register", course.getTitle());
                    CoursePaymentNotification dataPushNotify = CoursePaymentNotification.builder()
                            .type(PushNotificationType.PAYMENT_COURSE)
                            .status(OrderHistoryStatus.FAILED)
                            .courseId(course.getId())
                            .build();
                    firebaseService.pushNotification(PushNotification.builder()
                            .userId(user.getId())
                            .body("Bạn đã mua khoá học này trước đó")
                            .title("Mua không thành công")
                            .data(dataPushNotify.toMap())
                            .build());
                    return responseUtil.successResponse("ok");
                }
                userCourse.setCourseId(course.getId());
                userCourse.setUserId(user.getId());

                transaction.setAmount(course.getPrice());
                transaction.setNote(cassoResponse.data.get(0).description);
                transaction.setReferenceId(course.getId());
                transaction.setStatus(TransactionStatus.SUCCEED);
                transaction.setUserId(user.getId());
                transaction.setTransactionType(TransactionType.BUY_COURSE);

                transactionService.save(transaction);
                userCourseService.save(userCourse);
            } catch (Exception e) {
                log.error("Exception: {}", e.getMessage());
                assert user != null;
                CoursePaymentNotification dataPushNotify = CoursePaymentNotification.builder()
                        .type(PushNotificationType.PAYMENT_COURSE)
                        .status(OrderHistoryStatus.FAILED)
                        .courseId(course.getId())
                        .build();
                firebaseService.pushNotification(PushNotification.builder()
                        .userId(user.getId())
                                .title("Mua khoá học không thành công")
                        .body(String.format("Mua không thành công khoá '%s'", course.getTitle()))
                        .data(dataPushNotify.toMap())
                        .build());

                transaction.setAmount(course.getPrice());
                transaction.setNote(cassoResponse.data.get(0).description);
                transaction.setReferenceId(course.getId());
                transaction.setStatus(TransactionStatus.FAILED);
                transaction.setUserId(user.getId());
                transaction.setTransactionType(TransactionType.BUY_COURSE);
                transactionService.save(transaction);
                return responseUtil.successResponse("ok");
            }
            CoursePaymentNotification dataPushNotify = CoursePaymentNotification.builder()
                    .type(PushNotificationType.PAYMENT_COURSE)
                    .status(OrderHistoryStatus.SUCCESS)
                    .courseId(course.getId())
                    .build();
            firebaseService.pushNotification(PushNotification.builder()
                    .userId(user.getId())
                    .body(user.getFirstName() + " đã mua thành công khóa học " + course.getTitle())
                    .title("Mua khóa học thành công")
                    .data(dataPushNotify.toMap())
                    .build());
            return responseUtil.successResponse("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.successResponse("ok");
        }
    }


    private CassoKeys getKeyData(CassoResponse cassoResponse) {
        CassoKeys cassoKeys = new CassoKeys();
        String description = cassoResponse.data.get(0).description.toUpperCase();
        String[] parts = description.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            if(parts[i].startsWith("TP")) {
                cassoKeys.setCourseCode(parts[i]);
            }
            if(parts[i].startsWith("USER")) {
                cassoKeys.setUsername(parts[i]);
            }
        }
        return cassoKeys;
    }
}

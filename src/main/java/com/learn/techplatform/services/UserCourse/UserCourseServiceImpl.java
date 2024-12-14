package com.learn.techplatform.services.UserCourse;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.dto_modals.CourseDTO;
import com.learn.techplatform.dto_modals.UserCourseDTO;
import com.learn.techplatform.entities.UserCourse;
import com.learn.techplatform.repositories.UserCourseRepository;
import com.learn.techplatform.services.AbstractBaseService;
import com.learn.techplatform.services.Course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCourseServiceImpl  extends AbstractBaseService<UserCourse, String> implements UserCourseService {

    @Autowired
    UserCourseRepository userCourseRepository;

    public UserCourseServiceImpl(JpaRepository<UserCourse, String> genericRepository) {
        super(genericRepository);
    }

    @Override
    public UserCourse getByUserIdAndCourseId(String userId, String courseId, boolean throwError) {
        Optional<UserCourse> userCourse = userCourseRepository.findByCourseIdAndUserIdAndSystemStatus(courseId, userId, SystemStatus.ACTIVE);
        if (throwError && userCourse.isEmpty())
            Validator.notNull(null, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        return userCourse.orElse(null);
    }
}

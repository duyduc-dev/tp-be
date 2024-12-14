package com.learn.techplatform.services.UserCourse;

import com.learn.techplatform.dto_modals.UserCourseDTO;
import com.learn.techplatform.entities.UserCourse;
import com.learn.techplatform.services.InterfaceBaseService;

public interface UserCourseService extends InterfaceBaseService<UserCourse, String> {
    UserCourse getByUserIdAndCourseId(String userId, String courseId, boolean throwError);
}

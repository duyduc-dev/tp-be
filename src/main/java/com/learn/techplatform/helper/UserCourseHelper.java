package com.learn.techplatform.helper;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.enums.UserCourseStatus;
import com.learn.techplatform.common.utils.UniqueID;
import com.learn.techplatform.entities.UserCourse;
import org.springframework.stereotype.Component;

@Component
public class UserCourseHelper {
        public UserCourse createUserCourse(String CourseId, String userId) {
            return UserCourse.builder()
                    .courseId(CourseId)
                    .userId(userId)
                    .systemStatus(SystemStatus.ACTIVE)
                    .id(UniqueID.getUUID())
                    .build();
        }

}

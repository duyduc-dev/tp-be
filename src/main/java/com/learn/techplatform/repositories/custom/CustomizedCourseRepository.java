package com.learn.techplatform.repositories.custom;

import com.learn.techplatform.dto_modals.CourseDTO;
import org.springframework.data.repository.query.Param;

public interface CustomizedCourseRepository {
    CourseDTO getBySlug(@Param("slug") String slug, @Param("userId") String userId);
}

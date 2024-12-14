package com.learn.techplatform.controllers.models.response.course_response;

import com.learn.techplatform.dto_modals.ChapterDTO;

import java.util.List;

public class CourseContentResponse {
    private String id;
    private String title;
    private String slug;
    private List<ChapterDTO> chapters;
}

package com.learn.techplatform.controllers.models.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.utils.ParamError;
import com.learn.techplatform.common.validations.NullOrNotBlank.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class NewChapterRequest {
    @NotBlank(message = ParamError.FIELD_NAME)
    private String title;
    @NullOrNotBlank(message = ParamError.FIELD_NAME)
    private String previousChapterId;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String courseId;
}

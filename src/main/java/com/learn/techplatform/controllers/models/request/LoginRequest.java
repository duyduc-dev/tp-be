package com.learn.techplatform.controllers.models.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.constants.Constant;
import com.learn.techplatform.common.enums.UserRole;
import com.learn.techplatform.common.utils.ParamError;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {
    @NotBlank(message = ParamError.FIELD_NAME)
    @Pattern(regexp = Constant.EMAIL_REGEX, message = ParamError.PATTERN)
    @Size(max = 120, message = ParamError.MAX_LENGTH)
    private String username;

    @NotBlank(message = ParamError.FIELD_NAME)
    @Size(max = 50, message = ParamError.MAX_LENGTH)
    private String passwordHash;

    private UserRole userRole;
}

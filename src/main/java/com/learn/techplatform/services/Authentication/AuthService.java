package com.learn.techplatform.services.Authentication;

import com.learn.techplatform.common.utils.AppValueConfigure;
import com.learn.techplatform.controllers.models.request.*;
import com.learn.techplatform.controllers.models.response.AuthResponse;
import com.learn.techplatform.controllers.models.response.TokenResponse;
import com.learn.techplatform.dto_modals.UserDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService  {
    TokenResponse signUpUserVerify(UserDTO userDTO, HttpServletRequest request);
    AuthResponse confirmSignUpUser(ConfirmSignUpRequest confirmSignUpRequest, HttpServletRequest request);
    AuthResponse loginUser(LoginRequest loginRequest, HttpServletRequest request);
    AuthResponse googleLoginUser(GoogleLoginRequest loginRequest, HttpServletRequest request, AppValueConfigure appValueConfigure);
    void logout(String userId, HttpServletRequest request);
    TokenResponse forgotPassword(EmailRequest emailRequest, AppValueConfigure appValueConfigure, HttpServletRequest request);
    void resetPassword(ResetPasswordRequest resetPasswordRequest, HttpServletRequest request);

}

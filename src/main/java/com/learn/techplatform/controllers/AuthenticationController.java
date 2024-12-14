package com.learn.techplatform.controllers;


import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.enums.UserStatus;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.common.utils.AppUtil;
import com.learn.techplatform.controllers.models.request.*;
import com.learn.techplatform.dto_modals.UserDTO;
import com.learn.techplatform.entities.User;
import com.learn.techplatform.firebase.FirebaseService;
import com.learn.techplatform.firebase.modals.PushNotification;
import com.learn.techplatform.security.AuthSession;
import com.learn.techplatform.security.AuthUser;
import com.learn.techplatform.services.Authentication.AuthService;
import com.learn.techplatform.services.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(ApiPath.AUTHENTICATE_API)
public class AuthenticationController extends AbstractBaseController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping(ApiPath.LOGIN)
    @Operation(summary = "User Login")
    ResponseEntity<RestAPIResponse<Object>> signIn(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        return responseUtil.successResponse(authService.loginUser(loginRequest, request));
    }

    @PostMapping(ApiPath.GOOGLE_LOGIN)
    @Operation(summary = "Google Login")
    ResponseEntity<RestAPIResponse<Object>> signInGoogle(@Valid @RequestBody GoogleLoginRequest googleLoginRequest, HttpServletRequest request) {
        return responseUtil.successResponse(authService.googleLoginUser(googleLoginRequest,request, appValueConfigure));
    }

    @PostMapping(ApiPath.SIGN_UP)
    @Operation(summary = "User Confirm signup")
    ResponseEntity<RestAPIResponse<Object>> signUp(@Valid @RequestBody ConfirmSignUpRequest confirmSignUpRequest, HttpServletRequest request) {
        return responseUtil.successResponse(authService.confirmSignUpUser(confirmSignUpRequest, request));
    }

    @PostMapping(ApiPath.SIGNUP_VERIFY)
    @Operation(summary = "User verify signup")
    ResponseEntity<RestAPIResponse<Object>> signUpVerify(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletRequest request) {
        UserDTO userDTO = new UserDTO(signUpRequest);
        return responseUtil.successResponse(authService.signUpUserVerify(userDTO, request));
    }

    @GetMapping(ApiPath.AuthInFo)
    @Operation(summary = "Get Auth info")
    ResponseEntity<RestAPIResponse<Object>> authInfo(@Parameter(hidden = true) @AuthSession AuthUser auth) {
        User user = userService.getByIdAndUserStatus(auth.getId(), UserStatus.ACTIVE);
        String avatar = user.getProfileImage();
        log.info("URL >> {}", avatar);
        if((avatar == null || !AppUtil.isValidURL(avatar)) && user.getProfileImgPublicId() != null) {
            avatar = AppUtil.getUrlFile(appValueConfigure, user.getProfileImgPublicId());
        }
        user.setProfileImage(avatar);
        return responseUtil.successResponse(new UserDTO(user));
    }

    @DeleteMapping(ApiPath.LOGOUT)
    @Operation(summary = "Delete Session")
    ResponseEntity<RestAPIResponse<Object>> logout(@Parameter(hidden = true) @AuthSession AuthUser auth, HttpServletRequest request) {
        authService.logout(auth.getId(), request);
        return responseUtil.successResponse("ok");
    }

    @PutMapping(ApiPath.FORGOT_PASSWORD)
    @Operation(summary = "Forgot password")
    ResponseEntity<RestAPIResponse<Object>> forgotPassword(@Valid @RequestBody EmailRequest emailRequest, HttpServletRequest request) {
        return responseUtil.successResponse(authService.forgotPassword(emailRequest, appValueConfigure, request));
    }

    @PutMapping(ApiPath.RESET_PASSWORD)
    @Operation(summary = "Forgot password")
    ResponseEntity<RestAPIResponse<Object>> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest, HttpServletRequest request) {
        authService.resetPassword(resetPasswordRequest, request);
        return responseUtil.successResponse("ok");
    }

    @PostMapping(ApiPath.VALIDATE)
    @Operation(summary = "Validate")
    ResponseEntity<RestAPIResponse<Object>> validate(@Valid @RequestBody ValidateRequest validateRequest) {
        return responseUtil.successResponse("ok");
    }
}

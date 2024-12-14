package com.learn.techplatform.services.User;

import com.learn.techplatform.common.enums.UserRole;
import com.learn.techplatform.common.enums.UserStatus;
import com.learn.techplatform.controllers.models.request.EditUserRequest;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.UserDTO;
import com.learn.techplatform.entities.User;
import com.learn.techplatform.services.InterfaceBaseService;
import org.springframework.data.domain.Sort;

public interface UserService extends InterfaceBaseService<User, String> {
    UserDTO getUserByEmail(String email);
    UserDTO getAuthInfo(String id);
    User getByUsername(String username);
    User getByIdAndUserStatus(String id, UserStatus status);
    PagingResponse getPageUsers(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey, UserStatus userStatus, UserRole role);
    void deleteAccount(String id);
    void newUserSurvey(String userId);


    PagingResponse getPageUserCourses(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey, String userId);

}

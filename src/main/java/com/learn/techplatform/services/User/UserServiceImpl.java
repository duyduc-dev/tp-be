package com.learn.techplatform.services.User;

import com.learn.techplatform.common.enums.GenderType;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.enums.UserRole;
import com.learn.techplatform.common.enums.UserStatus;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.controllers.models.request.EditUserRequest;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.UserDTO;
import com.learn.techplatform.entities.Session;
import com.learn.techplatform.entities.User;
import com.learn.techplatform.repositories.UserCourseRepository;
import com.learn.techplatform.repositories.UserRepository;
import com.learn.techplatform.security.AuthHelper;
import com.learn.techplatform.services.AbstractBaseService;
import com.learn.techplatform.services.Session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractBaseService<User, String> implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserCourseRepository userCourseRepository;


    public UserServiceImpl(JpaRepository<User, String> genericRepository) {
        super(genericRepository);
    }


    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.getUserByEmailAndStatus(email, SystemStatus.ACTIVE, UserStatus.ACTIVE);
    }

    @Override
    public UserDTO getAuthInfo(String id) {
        return userRepository.getAuthInfo(id);
    }

    @Override
    public User getByUsername(String username) {
        User user = this.userRepository.findByUsernameAndSystemStatus(username, SystemStatus.ACTIVE);
        Validator.notNull(user, RestAPIStatus.NOT_FOUND, RestStatusMessage.NOT_FOUND);
        return user;
    }


    @Override
    public User getByIdAndUserStatus(String id, UserStatus status) {
        return userRepository.findByIdAndSystemStatusAndUserStatus(id, SystemStatus.ACTIVE, status);
    }


    @Override
    public PagingResponse getPageUsers(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey, UserStatus userStatus, UserRole role) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortType, "firstName"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        PagingResponse pagingResponse = new PagingResponse(userRepository.getPage("%" + searchKey + "%", userStatus , role, pageable));
        return pagingResponse;
    }


    @Override
    public void deleteAccount(String id) {
        User user = userRepository.findByIdAndSystemStatusAndUserStatus(id, SystemStatus.ACTIVE, UserStatus.ACTIVE);
        Validator.notNull(user, RestAPIStatus.NOT_FOUND, RestStatusMessage.USER_NOT_FOUND);
        user.setSystemStatus(SystemStatus.INACTIVE);
        user.setUserStatus(UserStatus.INACTIVE);
        this.save(user);
    }

    @Override
    public void newUserSurvey(String userId) {
        User user = userRepository.findByIdAndSystemStatusAndUserStatus(userId, SystemStatus.ACTIVE, UserStatus.ACTIVE);
        Validator.notNull(user, RestAPIStatus.NOT_FOUND, RestStatusMessage.USER_NOT_FOUND);
        user.setFirst(false);
        this.save(user);
    }

    @Override
    public PagingResponse getPageUserCourses(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey, String userId) {
        List<Sort.Order> orders = new ArrayList<>();
//        orders.add(new Sort.Order(sortType, ""));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        PagingResponse pagingResponse = new PagingResponse(userCourseRepository.getPage("%" + searchKey + "%", userId, pageable));
        return pagingResponse;
    }
}

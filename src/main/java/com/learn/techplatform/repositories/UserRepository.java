package com.learn.techplatform.repositories;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.enums.UserRole;
import com.learn.techplatform.common.enums.UserStatus;
import com.learn.techplatform.dto_modals.UserDTO;
import com.learn.techplatform.entities.Project;
import com.learn.techplatform.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = """
        SELECT NEW com.learn.techplatform.dto_modals.UserDTO(u)
        FROM User u
        WHERE u.email = :email AND u.systemStatus = :systemStatus AND u.userStatus = :userStatus
    """)
    UserDTO getUserByEmailAndStatus(@Param("email") String email, @Param("systemStatus") SystemStatus status, @Param("userStatus") UserStatus userStatus);

    @Query(value = """
        SELECT NEW com.learn.techplatform.dto_modals.UserDTO(u)
        FROM User u
        WHERE u.id = :userId AND u.systemStatus = 'ACTIVE'
    """)
    UserDTO getAuthInfo(@Param("userId") String id);

    User findByEmailAndSystemStatusAndUserStatus(String email, SystemStatus status, UserStatus userStatus);
    User findByEmailAndUserRoleAndSystemStatusAndUserStatus(String email, UserRole userRole, SystemStatus systemStatus, UserStatus userStatus);

    User findByIdAndSystemStatusAndUserStatus(String id, SystemStatus status, UserStatus userStatus);

    User findByUsernameAndSystemStatus(String username, SystemStatus systemStatus);

    @Query("""
        SELECT new com.learn.techplatform.dto_modals.UserDTO(u)
        FROM User u
        WHERE (u.firstName LIKE :search_key or u.lastName LIKE :search_key or u.email LIKE :search_key or u.username LIKE :search_key)
            AND u.userStatus = :user_status
            AND (u.userRole = :user_role or :user_role is null )
            AND u.systemStatus = 'ACTIVE'
   \s""")
    Page<UserDTO> getPage(
            @Param("search_key") String searchKey,
            @Param("user_status") UserStatus userStatus,
            @Param("user_role") UserRole role,
            Pageable pageable
    );
}

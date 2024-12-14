package com.learn.techplatform.repositories;

import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;

import com.learn.techplatform.dto_modals.TransactionDTO;
import com.learn.techplatform.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query("""
        SELECT new com.learn.techplatform.dto_modals.TransactionDTO(trans, user)
        FROM Transaction trans INNER JOIN User user
        On trans.userId = user.id
        WHERE trans.systemStatus = 'ACTIVE'\s
                AND (trans.transactionType = :type or :type IS NULL)\s
                AND (trans.status = :status OR :status IS NULL)
                AND user.systemStatus = 'ACTIVE'
                AND (user.firstName LIKE :search_key OR user.lastName LIKE :search_key OR user.username LIKE :search_key)
                AND (user.id = :user_id OR :user_id IS NULL)
                AND (trans.referenceId = :reference_id OR :reference_id IS NULL )
              \s
   \s""")
    Page<TransactionDTO> getPage(
            @Param("type") TransactionType type,
            @Param("status") TransactionStatus status,
            @Param("user_id") String userId,
            @Param("search_key") String searchKey,
            @Param("reference_id") String referenceId,
            Pageable pageable
    );
}

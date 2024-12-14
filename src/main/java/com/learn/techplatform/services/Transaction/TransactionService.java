package com.learn.techplatform.services.Transaction;

import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;
import com.learn.techplatform.common.enums.UserRole;
import com.learn.techplatform.common.enums.UserStatus;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.entities.Transaction;
import com.learn.techplatform.security.AuthUser;
import com.learn.techplatform.services.InterfaceBaseService;
import org.springframework.data.domain.Sort;

public interface TransactionService extends InterfaceBaseService<Transaction, String> {

    PagingResponse getPage(int pageNumber, int pageSize, Sort.Direction sortType,String searchKey, TransactionType type, TransactionStatus status, String userId, String referenceId);
}

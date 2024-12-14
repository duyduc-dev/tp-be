package com.learn.techplatform.services.Transaction;

import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.entities.Transaction;
import com.learn.techplatform.repositories.TransactionRepository;
import com.learn.techplatform.security.AuthUser;
import com.learn.techplatform.services.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl  extends AbstractBaseService<Transaction, String> implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(JpaRepository<Transaction, String> genericRepository) {
        super(genericRepository);
    }

    @Override
    public PagingResponse getPage(int pageNumber, int pageSize, Sort.Direction sortType,String searchKey, TransactionType type, TransactionStatus status, String userId, String referenceId) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortType, "createdDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        PagingResponse pagingResponse = new PagingResponse(transactionRepository.getPage(type , status, userId, "%"+ searchKey + "%",referenceId,  pageable));
        return pagingResponse;
    }
}

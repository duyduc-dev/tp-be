package com.learn.techplatform.controllers;

import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.security.AuthSession;
import com.learn.techplatform.security.AuthUser;
import com.learn.techplatform.security.AuthorizeValidator;
import com.learn.techplatform.services.Transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApiPath.TRANSACTION_API)
public class TransactionController extends AbstractBaseController{

    @Autowired
    TransactionService transactionService;

    @AuthorizeValidator()
    @GetMapping(ApiPath.GET_PAGE)
    ResponseEntity<RestAPIResponse<Object>> getAllTransaction(
            @RequestParam(name = "page_number", defaultValue = "1", required = false) int pageNumber,
            @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
            @RequestParam(name = "sort_type", defaultValue = "ASC", required = false) Sort.Direction sortType,
            @RequestParam(name = "search_key", defaultValue = "", required = false) String searchKey,
            @RequestParam(name = "transaction_status", required = false) TransactionStatus status,
            @RequestParam(name = "type", required = false) TransactionType type,
            @RequestParam(name = "reference_id", required = false) String referenceId,
            @RequestParam(name = "user_id", required = false) String userId
            ) {
        return responseUtil.successResponse(transactionService.getPage(pageNumber, pageSize, sortType,searchKey, type, status, userId, referenceId));
    }
}

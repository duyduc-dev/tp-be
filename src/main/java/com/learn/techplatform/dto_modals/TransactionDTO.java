package com.learn.techplatform.dto_modals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;
import com.learn.techplatform.entities.Transaction;
import com.learn.techplatform.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class TransactionDTO {
    private String id;
    private UserDTO user;
    private float amount;
    private String referenceId;
    private String note;
    private TransactionType transactionType;
    private TransactionStatus status;
    private SystemStatus systemStatus;
    private Long createAt;
    private Long updateAt;
    private Object detail;

    public TransactionDTO(Transaction transaction, User user) {
        this.id = transaction.getId();
        this.user = new UserDTO(user);
        this.amount = transaction.getAmount();
        this.referenceId = transaction.getReferenceId();
        this.note = transaction.getNote();
        this.transactionType = transaction.getTransactionType();
        this.status = transaction.getStatus();
        this.systemStatus = transaction.getSystemStatus();
        this.createAt = transaction.getCreatedDate().getTime();
        this.updateAt = transaction.getUpdatedDate() == null ? null : transaction.getUpdatedDate().getTime();
    }

    public TransactionDTO(Transaction transaction,User user, Object detail) {
        this.id = transaction.getId();
        this.user = new UserDTO(user);
        this.amount = transaction.getAmount();
        this.referenceId = transaction.getReferenceId();
        this.note = transaction.getNote();
        this.transactionType = transaction.getTransactionType();
        this.status = transaction.getStatus();
        this.systemStatus = transaction.getSystemStatus();
        this.createAt = transaction.getCreatedDate().getTime();
        this.updateAt = transaction.getUpdatedDate() == null ? null : transaction.getUpdatedDate().getTime();
        this.detail = detail;
    }
}

package com.learn.techplatform.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.techplatform.common.enums.GenderType;
import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@EntityListeners(AuditingEntityListener.class)
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "[transaction]")
public class Transaction extends AbstractBaseEntity<String> implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "amount")
    private float amount;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "note")
    private String note;

    @Column(name = "transaction_type", length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "status", length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}

package com.imalipay.loanapplication.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_loan_transaction")
public class LoanTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private double amount;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "reference", nullable = false)
    private String reference;
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    @JsonIgnore
    private Loan loan;
}

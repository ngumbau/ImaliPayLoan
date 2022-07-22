package com.imalipay.loanapplication.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(name = "interest", nullable = false)
    private double interest;
    @Column(name = "total_due", nullable = false)
    private double totalDue;
    @Column(name = "total_paid", nullable = false)
    private double totalPaid;
    @Column(name = "accrued_Interest", nullable = false)
    private double accruedInterest;
    @Column(name = "payment_due_date", nullable = true)
    private Date paymentDueDate;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name="loan_product_id")
    private LoanProduct loanProduct;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LoanTransaction> transactions;
}

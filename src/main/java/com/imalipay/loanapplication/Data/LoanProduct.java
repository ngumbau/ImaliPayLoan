package com.imalipay.loanapplication.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_loan_product")
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "loan_name", nullable = false)
    private String name;
    @Column(name = "minimum_amount", nullable = false)
    private double minimumAmount;
    @Column(name = "maximum_amount")
    private double maximumAmount;
    @Column(name = "interest_rate")
    private double interest_rate;       //Simple interest, Annual Rate
    @Column(name = "duration")
    private int Duration;
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}

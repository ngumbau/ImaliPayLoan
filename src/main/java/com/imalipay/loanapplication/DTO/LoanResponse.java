package com.imalipay.loanapplication.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanResponse {

    private Long loanId;
    private double amount;
    private double total_due;
    private double total_paid;
    private double total_balance;
    private double accrued_interest;
    private String repayment_due_date;
    private String state;
    private String created_at;
    private String updated_at;

}

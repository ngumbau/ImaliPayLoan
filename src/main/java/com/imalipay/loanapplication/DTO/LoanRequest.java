package com.imalipay.loanapplication.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoanRequest {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String productID;
    @NotEmpty
    private String amount;
}

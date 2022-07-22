package com.imalipay.loanapplication.DTO;

import lombok.Data;
import org.hibernate.dialect.Sybase11Dialect;

import javax.validation.constraints.NotEmpty;

@Data
public class LoanTransactionRequest {

    @NotEmpty
    private String loanId;
    @NotEmpty
    private String type;
    @NotEmpty
    private String amount;
    @NotEmpty
    private String paymentMethod;

}

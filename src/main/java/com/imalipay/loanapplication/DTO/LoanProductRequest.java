package com.imalipay.loanapplication.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class LoanProductRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String minimum;
    @NotEmpty
    private String maximum;
    @NotEmpty
    private String interest_rate;       //Simple interest, Annual Rate
    @NotEmpty
    private String duration;    //Duration in months.
}

package com.imalipay.loanapplication.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String nationalId;
}

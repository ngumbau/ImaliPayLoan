package com.imalipay.loanapplication.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long user_id;
    private String first_name;
    private String last_name;
    private String uri;
    private String created_at;
    private String updated_at;
}

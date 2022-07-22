package com.imalipay.loanapplication.Controller;

import com.imalipay.loanapplication.DTO.LoanProductRequest;
import com.imalipay.loanapplication.DTO.UserRequest;
import com.imalipay.loanapplication.DTO.UserResponse;
import com.imalipay.loanapplication.Data.User;
import com.imalipay.loanapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create a new user
     */
    @PostMapping
    @RequestMapping("/users")
    public ResponseEntity<UserResponse> createLoanProduct(@Valid @RequestBody UserRequest request,
                                                  UriComponentsBuilder uriComponentsBuilder){
        UserResponse response = userService.createUser(request);

        UriComponents uriComponents = uriComponentsBuilder.path("/users/{id}").buildAndExpand(response.getUser_id());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        response.setUri(uriComponents.toUriString());

        return new ResponseEntity<UserResponse>(response, headers,HttpStatus.CREATED);
    }

    /**
     * Show's a user's details
     */
    @GetMapping
    @RequestMapping("/users/{id}")
    public ResponseEntity<User> getLoanProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

}

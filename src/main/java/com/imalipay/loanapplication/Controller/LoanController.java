package com.imalipay.loanapplication.Controller;

import com.imalipay.loanapplication.DTO.LoanRequest;
import com.imalipay.loanapplication.Data.Loan;
import com.imalipay.loanapplication.Data.LoanProduct;
import com.imalipay.loanapplication.Data.User;
import com.imalipay.loanapplication.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * Create a new user loan account
     */
    @PostMapping
    @RequestMapping("/{user_id}/loans")
    public ResponseEntity<User> createLoan(@PathVariable("user_id")Long userId,
                                           @Valid @RequestBody LoanRequest request,
                                           UriComponentsBuilder uriComponentsBuilder){

        User response = loanService.createLoan(userId,request);

        UriComponents uriComponents = uriComponentsBuilder.path("/users/{userId}/loans/{id}")
                .buildAndExpand(userId,1);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<User>(response, headers, HttpStatus.CREATED);
    }

    /**
     * Show's a user's details
     */
    @GetMapping("/{user_id}/loans/{id}")
    public ResponseEntity<Loan> getLoanProduct(@PathVariable("user_id") Long userId,
                                                     @PathVariable("id") Long id){
        return ResponseEntity.ok(loanService.getLoanById(userId,id));
    }
}

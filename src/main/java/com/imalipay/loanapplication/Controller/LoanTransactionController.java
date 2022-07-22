package com.imalipay.loanapplication.Controller;

import com.imalipay.loanapplication.DTO.LoanTransactionRequest;
import com.imalipay.loanapplication.Data.LoanTransaction;
import com.imalipay.loanapplication.Service.LoanTransactionService;
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
@RequestMapping("/loan_transactions")
public class LoanTransactionController {

    @Autowired
    private LoanTransactionService loanTransactionService;

    /**
     * Create a new user loan transaction and update total amount due and total amount paid on a loan.
     */
    @PostMapping
    public ResponseEntity<LoanTransaction> createLoan(@Valid @RequestBody LoanTransactionRequest request,
                                           UriComponentsBuilder uriComponentsBuilder){
        LoanTransaction transaction = loanTransactionService.createLoanTransaction(request);
        Long loanId = Long.parseLong(request.getLoanId());

        UriComponents uriComponents = uriComponentsBuilder.path("/loans/{loan_id}/transactions/{id}")
                .buildAndExpand(loanId,transaction.getTransactionId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<LoanTransaction>(transaction,headers, HttpStatus.CREATED);
    }

    /**
     * Show's loan transaction details
     */
    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<LoanTransaction> getLoanProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(loanTransactionService.getUserById(id));
    }


}

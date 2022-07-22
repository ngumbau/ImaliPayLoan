package com.imalipay.loanapplication.Controller;

import com.imalipay.loanapplication.DTO.LoanProductRequest;
import com.imalipay.loanapplication.Data.LoanProduct;
import com.imalipay.loanapplication.Service.LoanProductService;
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
@RequestMapping("/loan_products")
public class LoanProductController {

    @Autowired
    private LoanProductService loanProductService;
    /**
     * List all available loan products
     */
    @GetMapping
    public ResponseEntity<List<LoanProduct>> getLoanProducts(){
        return ResponseEntity.ok(loanProductService.getLoanProducts());
    }

    /**
     * Show an available loan product
     */
    @GetMapping()
    @RequestMapping("/{id}")
    public ResponseEntity<LoanProduct> getLoanProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(loanProductService.getLoanProductByID(id));
    }

    /**
     * Create a loan product
     */
    @PostMapping
    public ResponseEntity<LoanProduct> createLoanProduct(@Valid @RequestBody LoanProductRequest loanProductRequest,
                                                  UriComponentsBuilder uriComponentsBuilder){
        LoanProduct response = loanProductService.createLoanProduct(loanProductRequest);

        UriComponents uriComponents = uriComponentsBuilder.path("/loan_products/{id}").buildAndExpand(response.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<LoanProduct>(response, headers, HttpStatus.CREATED);
    }

}

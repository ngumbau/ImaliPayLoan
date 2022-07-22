package com.imalipay.loanapplication.Service;

import com.imalipay.loanapplication.DTO.LoanProductRequest;
import com.imalipay.loanapplication.Data.LoanProduct;
import com.imalipay.loanapplication.Exception.LoanException;
import com.imalipay.loanapplication.Repository.LoanProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class LoanProductService {

    @Autowired
    private LoanProductRepository loanProductRepository;

    public LoanProduct createLoanProduct(LoanProductRequest request)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        LoanProduct product = LoanProduct.builder()
                .name(request.getName())
                .minimumAmount(Double.parseDouble(request.getMinimum()))
                .maximumAmount(Double.parseDouble(request.getMaximum()))
                .interest_rate(Double.parseDouble(request.getInterest_rate()))
                .Duration(Integer.parseInt(request.getDuration()))
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        product = loanProductRepository.save(product);

        return product;
    }

    public List<LoanProduct> getLoanProducts() {
        return loanProductRepository.findAll();
    }

    public LoanProduct getLoanProductByID(Long id) {
        Optional<LoanProduct> requestedLoanProduct = loanProductRepository.findById(id);

        if(requestedLoanProduct.isEmpty()){
            throw new LoanException(String.format("Loan Product with ID > '%s' cannot be found", id));
        }
        return requestedLoanProduct.get();
    }
}

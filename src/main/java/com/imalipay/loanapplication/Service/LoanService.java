package com.imalipay.loanapplication.Service;

import com.imalipay.loanapplication.DTO.LoanRequest;
import com.imalipay.loanapplication.DTO.LoanResponse;
import com.imalipay.loanapplication.DTO.UserResponse;
import com.imalipay.loanapplication.Data.Loan;
import com.imalipay.loanapplication.Data.LoanProduct;
import com.imalipay.loanapplication.Data.User;
import com.imalipay.loanapplication.Exception.LoanException;
import com.imalipay.loanapplication.Repository.LoanProductRepository;
import com.imalipay.loanapplication.Repository.LoanRepository;
import com.imalipay.loanapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanProductRepository loanProductRepository;
    @Autowired
    private UserRepository userRepository;
    public User createLoan(Long userId, LoanRequest request) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Long productId = Long.parseLong(request.getProductID());
        Optional<LoanProduct> product = loanProductRepository.findById(productId);

        if(product.isEmpty()){
            throw new LoanException(String.format("Loan Product with ID > '%s' cannot be found", productId));
        }

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new LoanException(String.format("User with ID > '%s cannot be found", userId));
        }

        double amount = Double.parseDouble(request.getAmount());
        double interest = amount * (product.get().getInterest_rate() / 100) * (product.get().getDuration() / 12.0);
        double totalDue = amount + interest;

        if(amount > product.get().getMaximumAmount() ){
            throw new LoanException(String.format("Amount requested '%s' is greater than loan maximum '%s",
                    amount, product.get().getMaximumAmount()));
        }else if (amount < product.get().getMinimumAmount()){
            throw new LoanException(String.format("Amount requested '%s' is lesser than loan minimum '%s'",
                    amount, product.get().getMaximumAmount()));
        }

        Loan loan  = Loan.builder()
                .user(user.get())
                .loanProduct(product.get())
                .amount(amount)
                .interest(interest)
                .totalDue(totalDue)
                .state("Approved")
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        Calendar cal =Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MONTH, product.get().getDuration());
        timestamp = new Timestamp(cal.getTime().getTime());
        loan.setPaymentDueDate(new Date(timestamp.getTime()));

        loan = loanRepository.save(loan);

        return user.get();
    }

    public Loan getLoanById(Long userId, Long id) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new LoanException(String.format("user  with ID > '%s' cannot be found", id));
        }

        Loan requestedLoan = null;

        for (Loan loan: user.get().getLoans()){
            if(loan.getLoanId().equals(id)){
                requestedLoan = loan;
            }
        }

        if (requestedLoan == null){
            throw new LoanException(String.format("Loan with ID > '%s' cannot be found", id));
        }

        return requestedLoan;
    }
}

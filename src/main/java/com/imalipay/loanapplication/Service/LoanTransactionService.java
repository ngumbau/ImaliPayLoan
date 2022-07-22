package com.imalipay.loanapplication.Service;

import com.imalipay.loanapplication.DTO.LoanTransactionRequest;
import com.imalipay.loanapplication.Data.Loan;
import com.imalipay.loanapplication.Data.LoanTransaction;
import com.imalipay.loanapplication.Exception.LoanException;
import com.imalipay.loanapplication.Repository.LoanRepository;
import com.imalipay.loanapplication.Repository.LoanTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanTransactionService {

    @Autowired
    private LoanTransactionRepository loanTransactionRepository;
    @Autowired
    private LoanRepository loanRepository;


    public LoanTransaction createLoanTransaction(LoanTransactionRequest request) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Optional<Loan> loan = loanRepository.findById(Long.parseLong(request.getLoanId()));
        if(loan.isEmpty()){
            throw new LoanException(String.format("Loan with ID > '%s cannot be found", loan));
        }
        LoanTransaction transaction = LoanTransaction.builder()
                .loan(loan.get())
                .reference(UUID.randomUUID().toString())
                .amount(Double.parseDouble(request.getAmount()))
                .paymentMethod(request.getPaymentMethod())
                .type(request.getType())
                .createdAt(timestamp)
                .updatedAt(timestamp)
                .build();

        double totalPaid = loan.get().getTotalPaid() + transaction.getAmount();
        double totalDue = loan.get().getTotalDue() - transaction.getAmount();

        loan.get().setTotalPaid(totalPaid);
        loan.get().setTotalDue(totalDue);

        transaction = loanTransactionRepository.save(transaction);

        return transaction;
    }

    public LoanTransaction getUserById(Long id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Optional<LoanTransaction> transaction = loanTransactionRepository.findById(id);

        if(transaction.isEmpty()){
            throw new LoanException(String.format("Transaction with ID > '%s cannot be found", id));
        }

        return transaction.get();
    }

}

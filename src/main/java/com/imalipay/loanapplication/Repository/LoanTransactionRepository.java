package com.imalipay.loanapplication.Repository;

import com.imalipay.loanapplication.Data.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {
}

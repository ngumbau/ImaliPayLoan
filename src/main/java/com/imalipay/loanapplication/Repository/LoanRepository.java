package com.imalipay.loanapplication.Repository;

import com.imalipay.loanapplication.Data.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}

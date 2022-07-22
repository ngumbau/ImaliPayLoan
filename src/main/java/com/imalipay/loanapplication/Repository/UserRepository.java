package com.imalipay.loanapplication.Repository;

import com.imalipay.loanapplication.Data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

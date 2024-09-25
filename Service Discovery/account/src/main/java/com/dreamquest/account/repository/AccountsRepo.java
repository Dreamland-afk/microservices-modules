package com.dreamquest.account.repository;

import com.dreamquest.account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerID(Long accountNumber);

    void deleteByCustomerID(Long customerID);
}

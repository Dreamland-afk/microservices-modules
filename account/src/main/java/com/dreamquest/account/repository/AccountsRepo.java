package com.dreamquest.account.repository;

import com.dreamquest.account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts,Long> {
}

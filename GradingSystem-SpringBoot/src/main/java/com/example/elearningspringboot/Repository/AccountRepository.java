package com.example.elearningspringboot.Repository;

import com.example.elearningspringboot.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    static final String SELECT_ACCOUNT_BY_USERNAME_SQL = "SELECT * FROM ACCOUNT WHERE username =?1";
    @Query(value = SELECT_ACCOUNT_BY_USERNAME_SQL, nativeQuery = true)
    Account findUsersByUsername(String username);
}
package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Account;

public interface AccountService {
    Account findUsersByUsername(String username);
    Account checkUserCredentials(String username, String password);
    Account createUserCredentials(Account account,String confirmPassword);
}

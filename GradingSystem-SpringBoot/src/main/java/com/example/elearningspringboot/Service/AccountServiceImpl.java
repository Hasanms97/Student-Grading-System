package com.example.elearningspringboot.Service;

import com.example.elearningspringboot.Entity.Account;
import com.example.elearningspringboot.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account findUsersByUsername(String username) {
        return accountRepository.findUsersByUsername(username);
    }

    @Override
    public Account checkUserCredentials(String username, String password) {
        Account account = findUsersByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    @Override
    public Account createUserCredentials(Account account, String confirmPassword) {
        if (findUsersByUsername(account.getUsername()) == null) {
            if (account.getPassword().equals(confirmPassword)) {
                accountRepository.save(account);
                return account;
            }
        }
        return null;
    }


}

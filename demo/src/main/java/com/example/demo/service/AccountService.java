package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * Create a new account.
     *
     * @param account The account object to save.
     * @return The saved account.
     */
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Retrieve all accounts.
     *
     * @return List of all accounts.
     */
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    /**
     * Retrieve an account by ID.
     *
     * @param id The account ID.
     * @return The account object if found.
     */
    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    /**
     * Update an existing account.
     *
     * @param id The account ID.
     * @param updatedAccount The updated account object.
     * @return The updated account if found.
     */
    public Account updateAccount(Integer id, Account updatedAccount) {
        return accountRepository.findById(id)
                .map(existingAccount -> {
                    existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
                    return accountRepository.save(existingAccount);
                })
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    /**
     * Delete an account by ID.
     *
     * @param id The account ID to delete.
     */
    public void deleteAccount(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with ID: " + id);
        }
        accountRepository.deleteById(id);
    }
}


package me.sedov.TestWorkForPioneer.service.impl;

import me.sedov.TestWorkForPioneer.model.Account;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.repository.AccountRepository;
import me.sedov.TestWorkForPioneer.repository.UserRepository;
import me.sedov.TestWorkForPioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void transferMoney(Long fromUserId, Long toUserId, BigDecimal amount) {
        // Логика перевода денег с валидацией и обработкой ошибок.
        // Пример:
        Account fromAccount = accountRepository.findById(fromUserId).orElseThrow();
        Account toAccount = accountRepository.findById(toUserId).orElseThrow();

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Недостаточно средств");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

    }

    @Override
    public List<User> searchUsers(String name) {
        return userRepository.findAll();
    }

    @Override
    public void updateEmail(Long userId, String newEmail) {

    }

    @Override
    public void updatePhone(Long userId, String newPhone) {

    }
}

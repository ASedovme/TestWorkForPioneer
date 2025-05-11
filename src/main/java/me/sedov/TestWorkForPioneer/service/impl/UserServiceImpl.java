package me.sedov.TestWorkForPioneer.service.impl;

import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.exception.EntityNotFoundException;
import me.sedov.TestWorkForPioneer.exception.UserNotFoundException;
import me.sedov.TestWorkForPioneer.model.Account;
import me.sedov.TestWorkForPioneer.model.EmailData;
import me.sedov.TestWorkForPioneer.model.PhoneData;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.repository.AccountRepository;
import me.sedov.TestWorkForPioneer.repository.EmailDataRepository;
import me.sedov.TestWorkForPioneer.repository.PhoneDataRepository;
import me.sedov.TestWorkForPioneer.repository.UserRepository;
import me.sedov.TestWorkForPioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private final EmailDataRepository emailDataRepository;

    @Autowired
    private final PhoneDataRepository phoneDataRepository;


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
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void updateEmail(Long userId, String newEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с ID: " + userId));

        // Проверяем, существует ли уже запись email для данного пользователя
        EmailData emailData = (EmailData) emailDataRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Email не найден для пользователя с ID: " + userId));

        // Обновляем email
        emailData.setEmail(newEmail);
        emailDataRepository.save(emailData);


    }

    @Override
    public void updatePhone(Long userId, String newPhone) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с ID: " + userId));

        // Проверяем, существует ли уже запись телефона для данного пользователя
        PhoneData phoneData = phoneDataRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Телефон не найден для пользователя с ID: " + userId));

        // Обновляем телефон
        phoneData.setPhone(newPhone);
        phoneDataRepository.save(phoneData);
    }


    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}

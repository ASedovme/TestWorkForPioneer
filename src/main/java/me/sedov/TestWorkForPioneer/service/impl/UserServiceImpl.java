package me.sedov.TestWorkForPioneer.service.impl;

import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.exception.EntityNotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
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
    public void updateEmail(Long userId, String newEmail) {
        User user = (User) userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с ID: " + userId));

        // Проверка на уникальность нового email
        if (emailDataRepository.existsByEmail(newEmail)) {
            throw new RuntimeException("Email уже используется");
        }

        EmailData emailData = (EmailData) emailDataRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Email не найден для пользователя с ID: " + userId));

        // Обновление email
        emailData.setEmail(newEmail);
        emailDataRepository.save(emailData);
    }

    @Override
    public void updatePhone(Long userId, String newPhone) {
        User user = (User) userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с ID: " + userId));

        // Проверка на уникальность нового номера телефона
        if (phoneDataRepository.existsByPhone(newPhone)) {
            throw new RuntimeException("Номер телефона уже используется");
        }

        PhoneData phoneData = phoneDataRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Телефон не найден для пользователя с ID: " + userId));

        // Обновление номера телефона
        phoneData.setPhone(newPhone);
        phoneDataRepository.save(phoneData);
    }

    @Override
    public void incrementBalances() {
        List<Account> accounts = accountRepository.findAll();

        for (Account account : accounts) {
            BigDecimal currentBalance = account.getBalance();
            BigDecimal initialDeposit = account.getInitialDeposit(); // Получаем текущее значение баланса

            BigDecimal maxAllowedBalance = initialDeposit.multiply(BigDecimal.valueOf(2.07));
            if (currentBalance.compareTo(maxAllowedBalance) < 0) {
                BigDecimal incrementAmount = currentBalance.multiply(BigDecimal.valueOf(0.10));
                account.setBalance(currentBalance.add(incrementAmount));
                accountRepository.save(account);
            }
        }
    }
    public Page<User> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> searchByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<User> searchByEmail(String email) {
        return emailDataRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<User> searchByPhone(String phone) {
        Long id = phoneDataRepository.findUserIdByPhone(phone);
        if (id == null) {
            return Collections.emptyList();
        }
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);
    }

    @Override
    public List<User> searchByDateOfBirth(LocalDate dateOfBirth) {
        return userRepository.findByDateOfBirth(dateOfBirth);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}

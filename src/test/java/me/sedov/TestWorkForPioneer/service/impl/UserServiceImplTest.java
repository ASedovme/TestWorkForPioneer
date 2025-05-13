package me.sedov.TestWorkForPioneer.service.impl;

import me.sedov.TestWorkForPioneer.model.Account;
import me.sedov.TestWorkForPioneer.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UserServiceImpl userService; // замените на ваш класс

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferMoney() {
        Long fromUserId = 1L;
        Long toUserId = 2L;
        BigDecimal amount = new BigDecimal("50.00");

        // Создаем аккаунты для теста
        Account fromAccount = new Account();
        fromAccount.setId(fromUserId);
        fromAccount.setBalance(new BigDecimal("100.00"));

        Account toAccount = new Account();
        toAccount.setId(toUserId);
        toAccount.setBalance(new BigDecimal("20.00"));

        // Мокаем вызовы репозитория
        when(accountRepository.findById(fromUserId)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(toUserId)).thenReturn(Optional.of(toAccount));

        // Вызов метода
        userService.transferMoney(fromUserId, toUserId, amount);

        // Проверяем изменения балансов
        assertEquals(new BigDecimal("50.00"), fromAccount.getBalance());
        assertEquals(new BigDecimal("70.00"), toAccount.getBalance());

        // Проверяем, что сохранение вызвано
        verify(accountRepository).save(fromAccount);
        verify(accountRepository).save(toAccount);
    }
}
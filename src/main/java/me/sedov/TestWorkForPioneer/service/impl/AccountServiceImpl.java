package me.sedov.TestWorkForPioneer.service.impl;

import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.model.Account;
import me.sedov.TestWorkForPioneer.repository.AccountRepository;
import me.sedov.TestWorkForPioneer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

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

}

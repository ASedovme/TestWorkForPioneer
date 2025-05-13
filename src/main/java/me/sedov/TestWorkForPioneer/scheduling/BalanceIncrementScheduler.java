package me.sedov.TestWorkForPioneer.scheduling;

import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.service.AccountService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BalanceIncrementScheduler {

    private final AccountService accountService;

    @Scheduled(fixedDelay = 30000)
    public void runIncrementBalances() {
        accountService.incrementBalances();
    }
}

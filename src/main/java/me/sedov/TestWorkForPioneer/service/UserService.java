package me.sedov.TestWorkForPioneer.service;

import me.sedov.TestWorkForPioneer.model.User;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUser(Long id);

    public void transferMoney(Long fromUserId, Long toUserId, BigDecimal amount);


    public void updateEmail(Long userId, String newEmail);

    public void updatePhone(Long userId, String newPhone);

    void incrementBalances();

    Page<User> findAllUsers(int page, int size);

    public List<User> searchUser(String name, String email, String phone, LocalDateTime dateOfBirth);
}

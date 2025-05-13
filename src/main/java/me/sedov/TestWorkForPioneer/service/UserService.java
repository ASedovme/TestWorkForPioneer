package me.sedov.TestWorkForPioneer.service;

import me.sedov.TestWorkForPioneer.model.User;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUser(Long id);

    public void transferMoney(Long fromUserId, Long toUserId, BigDecimal amount);


    public void updateEmail(Long userId, String newEmail);

    public void updatePhone(Long userId, String newPhone);


    List<User> searchByName(String name);

    List<User> searchByEmail(String email);

    List<User> searchByPhone(String phone);

    List<User> searchByDateOfBirth(LocalDate dateOfBirth);

    List<User> getAllUsers();
}

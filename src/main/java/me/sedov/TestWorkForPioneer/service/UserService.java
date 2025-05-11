package me.sedov.TestWorkForPioneer.service;

import me.sedov.TestWorkForPioneer.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUser(Long id);

    public void transferMoney(Long fromUserId, Long toUserId, BigDecimal amount);

    public List<User> searchUsers(String name);

    public void updateEmail(Long userId, String newEmail);

    public void updatePhone(Long userId, String newPhone);
}

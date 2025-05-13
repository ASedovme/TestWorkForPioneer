package me.sedov.TestWorkForPioneer.service;

import me.sedov.TestWorkForPioneer.model.User;

import java.util.Optional;

public interface AuthService {
    public Optional<User> authenticate(String email, String password);
}

package me.sedov.TestWorkForPioneer.service.impl;

import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.model.EmailData;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.repository.EmailDataRepository;
import me.sedov.TestWorkForPioneer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final EmailDataRepository emailDataRepository;

    @Override
    public Optional<User> authenticate(String email, String password) {
        Optional<EmailData> emailDataOpt= emailDataRepository.findByEmail(email);
        if (emailDataOpt.isPresent()) {
            User user= emailDataOpt.get().getUser();
            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}

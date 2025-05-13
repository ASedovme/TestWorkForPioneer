package me.sedov.TestWorkForPioneer.controller;

import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.model.JwtResponse;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.service.AuthService;
import me.sedov.TestWorkForPioneer.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = JwtUtil.generateToken(user.getId());
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Invalid credentials");
            return ResponseEntity.status(401).body(errorBody);
        }
    }
}
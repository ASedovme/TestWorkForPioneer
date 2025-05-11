package me.sedov.TestWorkForPioneer.controller;


import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.dto.TransferRequest;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUser(id));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest request) {
        userService.transferMoney(request.getFromUserId(), request.getToUserId(), request.getAmount());
        return ResponseEntity.ok("Перевод выполнен успешно");
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String name) {
        List<User> users = userService.searchUsers(name);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<String> updateEmail(@PathVariable Long userId, @RequestParam String newEmail) {
        userService.updateEmail(userId, newEmail);
        return ResponseEntity.ok("Email обновлен успешно");
    }

    @PutMapping("/{userId}/phone")
    public ResponseEntity<String> updatePhone(@PathVariable Long userId, @RequestParam String newPhone) {
        userService.updatePhone(userId, newPhone);
        return ResponseEntity.ok("Телефон обновлен успешно");
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser); // Возвращаем созданного пользователя с HTTP статусом 200 OK
    }
}

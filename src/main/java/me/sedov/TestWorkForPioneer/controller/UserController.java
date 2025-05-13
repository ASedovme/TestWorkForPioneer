package me.sedov.TestWorkForPioneer.controller;


import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.dto.TransferRequest;
import me.sedov.TestWorkForPioneer.dto.UserDTO;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> searchUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dateOfBirth) {

        try {
            if ((name == null || name.isEmpty()) &&
                    (email == null || email.isEmpty()) &&
                    (phone == null || phone.isEmpty()) &&
                    dateOfBirth == null) {
                return ResponseEntity.badRequest().body("Задайте параметры поиска");
            }

            List<User> users;

            if (name != null && !name.isEmpty()) {
                users = userService.searchByName(name);
            } else if (email != null && !email.isEmpty()) {
                users = userService.searchByEmail(email);
            } else if (phone != null && !phone.isEmpty()) {
                users = userService.searchByPhone(phone);
            } else if (dateOfBirth != null) {
                users = userService.searchByDateOfBirth(dateOfBirth);
            } else {
                users = userService.getAllUsers();
            }

            if (!users.isEmpty()) {
                List<UserDTO> userDTOs = users.stream()
                        .map(user -> new UserDTO(user.getId(), user.getName(), user.getDateOfBirth(),
                                user.getAccount(), user.getEmailDataList(),
                                user.getPhoneDataList()))
                        .collect(Collectors.toList());
                return ResponseEntity.ok(userDTOs);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователи не найдены");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Или используйте логгер
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
        }
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

}

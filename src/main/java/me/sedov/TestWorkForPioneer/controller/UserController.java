package me.sedov.TestWorkForPioneer.controller;


import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.dto.TransferRequest;
import me.sedov.TestWorkForPioneer.dto.UserDTO;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<UserDTO>> searchUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) LocalDateTime dateOfBirth) {

        try {
            List<User> users = userService.searchUser(name, email, phone, dateOfBirth);

            if (!users.isEmpty()) {
                List<UserDTO> userDTOs = users.stream()
                        .map(user -> new UserDTO(user.getId(), user.getName(), user.getDateOfBirth(),
                                user.getAccount(), user.getEmailDataList(),
                                user.getPhoneDataList()))
                        .collect(Collectors.toList());
                return ResponseEntity.ok(userDTOs);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace(); // Или используйте логгер
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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

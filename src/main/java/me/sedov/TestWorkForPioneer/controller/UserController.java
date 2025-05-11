package me.sedov.TestWorkForPioneer.controller;


import lombok.RequiredArgsConstructor;
import me.sedov.TestWorkForPioneer.dto.TransferRequest;
import me.sedov.TestWorkForPioneer.model.User;
import me.sedov.TestWorkForPioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

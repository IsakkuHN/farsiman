package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import com.github.isakkuhn.farsimanevaluacion.business.services.UserService;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users =  this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable UUID id) {
        UserEntity user = this.userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserEntity user) {
        UserEntity userEntity = this.userService.createUser(user);

        if(userEntity == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(userEntity);
    }

    @PutMapping
    public ResponseEntity<UserEntity> updateUser(@RequestBody @Valid UserEntity user) {
        UserEntity userEntity = this.userService.updateUser(user);
        if(userEntity == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable UUID id) {
        boolean deleted = this.userService.deleteUser(id);
        if(deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

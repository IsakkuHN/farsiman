package com.github.isakkuhn.farsimanevaluacion.business.services;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.UserEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity createUser(UserEntity user) {
        if(user.getId()== null) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            return this.userRepository.save(user);
        }
        return null;
    }

    public UserEntity updateUser(UserEntity user) {
        UserEntity userEntity = this.userRepository.findById(user.getId()).orElse(null);
        if(userEntity != null) {
            return this.userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(UUID id) {
        UserEntity userEntity = this.userRepository.findById(id).orElse(null);
        if(userEntity != null) {
            this.userRepository.delete(userEntity);
            return true;
        }
        return false;
    }

}

package com.github.isakkuhn.farsimanevaluacion.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "El nombnre de usuario no debe ser una cadena vacia. ")
    @NotNull(message = "El campo de username es requerido.")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "La contraseña no debe de ser una cadena vacia. ")
    @NotNull(message = "La constraseña es un campo requerido.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "El usuario debe de contener un rol especifico.")
    @NotNull(message = "El rol es un campo obligatorio para cada usuario.")
    @Column(nullable = false)
    private String role;

    @Transient
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void EncryptPassword() {
        this.password = passwordEncoder.encode(this.password);
    }

    public boolean DecryptPassword(String plainPassword) {
        return passwordEncoder.matches(plainPassword, this.password);
    }



}

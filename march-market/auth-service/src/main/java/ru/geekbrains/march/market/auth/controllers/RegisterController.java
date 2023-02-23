package ru.geekbrains.march.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.JwtRequest;
import ru.geekbrains.march.market.api.JwtResponse;
import ru.geekbrains.march.market.api.RegisterUserDto;
import ru.geekbrains.march.market.auth.entities.Role;
import ru.geekbrains.march.market.auth.entities.User;
import ru.geekbrains.march.market.auth.exceptions.AppError;
import ru.geekbrains.march.market.auth.services.RoleService;
import ru.geekbrains.march.market.auth.services.UserService;
import ru.geekbrains.march.market.auth.utils.JwtTokenUtil;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registrateNewUser(@RequestBody RegisterUserDto registerUserDto) {

        // TODO полностью реализовать метод, как считаете нужным
        //  ниже всего лишь пример хеширования паролей

        if (userService.findByUsername(registerUserDto.getUsername()).isPresent()) {
            throw new IllegalStateException("Пользователь с этим именем уже зарегистрирован.");
        }
        if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            throw new IllegalStateException("Пароль и его подтверждение не совпадают.");
        }
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleService.getUserRole());
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        String bcryptCachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
        user.setPassword(bcryptCachedPassword);
        user.setEmail(registerUserDto.getEmail());
        user.setRoles(roles);
        return userService.saveNewUser(user);
    }
}

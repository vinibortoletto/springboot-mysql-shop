package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.config.security.TokenService;
import com.vinibortoletto.simpleshop.dtos.user.AuthenticationRequestDTO;
import com.vinibortoletto.simpleshop.dtos.user.AuthenticationResponseDTO;
import com.vinibortoletto.simpleshop.dtos.user.UserRequestDTO;
import com.vinibortoletto.simpleshop.dtos.user.UserResponseDTO;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "users")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Returns all users")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserResponseDTO> response = UserResponseDTO.convert(userList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Returns a user based on its id")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String userId) {
        User user = userService.findById(userId);
        UserResponseDTO response = new UserResponseDTO(user);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Creates a new user")
    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO dto) {
        User user = userService.save(dto);
        UserResponseDTO response = new UserResponseDTO(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Updates a user based on its id")
    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserRequestDTO dto, @PathVariable String userId) {
        User user = userService.update(dto, userId);
        UserResponseDTO response = new UserResponseDTO(user);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Deletes a user based on its id")
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> delete(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Validate user login")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        AuthenticationResponseDTO response = new AuthenticationResponseDTO(token);

        return ResponseEntity.ok().body(response);
    }
}

package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.UserRequestDTO;
import com.vinibortoletto.simpleshop.dtos.UserResponseDTO;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "users")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Returns all users")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserResponseDTO> response = UserResponseDTO.convert(userList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Returns a user based on its id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
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
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserRequestDTO dto, @PathVariable String id) {
        User user = userService.update(dto, id);
        UserResponseDTO response = new UserResponseDTO(user);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Deletes a user based on its id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

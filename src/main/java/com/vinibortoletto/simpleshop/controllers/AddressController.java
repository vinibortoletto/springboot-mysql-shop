package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.AddressRequestDTO;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "addresses")
@RestController
@RequestMapping(value = "addresses")
public class AddressController {
    @Autowired
    private AddressService service;

    @Operation(summary = "Creates a new address for user")
    @PostMapping(value = "/{userId}")
    public ResponseEntity<Address> save(@RequestBody @Valid AddressRequestDTO dto, @PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto, userId));
    }
}

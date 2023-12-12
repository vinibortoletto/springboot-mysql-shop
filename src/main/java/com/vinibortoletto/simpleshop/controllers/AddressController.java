package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.AddressRequestDTO;
import com.vinibortoletto.simpleshop.dtos.AddressResponseDTO;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "addresses")
@RestController
@RequestMapping(value = "addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Operation(summary = "Creates a new address for user")
    @PostMapping
    public ResponseEntity<AddressResponseDTO> save(@RequestBody @Valid AddressRequestDTO dto) {
        Address address = addressService.save(dto);
        AddressResponseDTO response = new AddressResponseDTO(address, dto.customerId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

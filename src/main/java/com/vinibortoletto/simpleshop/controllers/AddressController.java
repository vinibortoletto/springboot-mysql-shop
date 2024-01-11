package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.address.AddressRequestDTO;
import com.vinibortoletto.simpleshop.dtos.address.AddressResponseDTO;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "addresses")
@RestController
@RequestMapping(value = "addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Operation(summary = "Finds all addresses")
    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAll() {
        List<Address> addressList = addressService.findAll();
        List<AddressResponseDTO> response = AddressResponseDTO.convert(addressList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Returns an address based on its id")
    @GetMapping(value = "/{addressId}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable String addressId) {
        Address address = addressService.findById(addressId);
        AddressResponseDTO response = new AddressResponseDTO(address);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Returns all addresses based on the customer id")
    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<List<AddressResponseDTO>> findAllByCustomerId(@PathVariable String customerId) {
        List<Address> addressList = addressService.findAllByCustomerId(customerId);
        List<AddressResponseDTO> response = AddressResponseDTO.convert(addressList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Creates a new address for user")
    @PostMapping
    public ResponseEntity<AddressResponseDTO> save(@RequestBody @Valid AddressRequestDTO dto) {
        Address address = addressService.save(dto);
        AddressResponseDTO response = new AddressResponseDTO(address);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "Updates an user address")
    @PutMapping(value = "/{addressId}")
    public ResponseEntity<AddressResponseDTO> update(@RequestBody @Valid AddressRequestDTO dto, String addressId) {
        Address address = addressService.update(dto, addressId);
        AddressResponseDTO response = new AddressResponseDTO(address);

        return ResponseEntity.ok().body(response);
    }
}

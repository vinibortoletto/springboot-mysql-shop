package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.services.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "addresses")
@RestController
@RequestMapping(value = "addresses")
public class AddressController {
    @Autowired
    private AddressService service;

//    @Operation(summary = "Creates a new address for user")
//    @PostMapping(value = "/{userId}")
//    public ResponseEntity<Address> save(@RequestBody @Valid AddressDto dto, @PathVariable String userId) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto, userId));
//    }
}

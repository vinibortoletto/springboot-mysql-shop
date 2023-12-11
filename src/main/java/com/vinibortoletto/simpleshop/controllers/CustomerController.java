package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.CustomerResponseDTO;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "customers")
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Returns all users")
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        List<Customer> userList = customerService.findAll();
        List<CustomerResponseDTO> response = CustomerResponseDTO.convert(userList);

        return ResponseEntity.ok().body(response);
    }

    //    @Operation(summary = "Returns a user based on its id")
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<User> findById(@PathVariable String id) {
//        User user = service.findById(id);
//        return ResponseEntity.ok().body(user);
//    }
//
//    @Operation(summary = "Creates a new user")
//    @PostMapping
//    public ResponseEntity<CustomerResponseDTO> save(@RequestBody @Valid CustomerRequestDTO dto) {
//        Customer customer = service.save(dto);
//        CustomerResponseDTO response = new CustomerResponseDTO(customer);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @Operation(summary = "Updates a user based on its id")
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<User> update(@RequestBody @Valid UserDto dto, @PathVariable String id) {
//        return ResponseEntity.ok().body(service.update(dto, id));
//    }
//
//    @Operation(summary = "Deletes a user based on its id")
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable String id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}

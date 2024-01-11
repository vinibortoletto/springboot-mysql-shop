package com.vinibortoletto.simpleshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Vinicius Bortoletto",
            email = "ovinibortoletto@gmail.com"
        ),
        description = "Simple Shop API",
        title = "Simple Shop API",
        version = "1.0.0"
    ),
    security = {
        @SecurityRequirement(name = "Bearer Authentication")
    }
)
@SecurityScheme(
    name = "Bearer Authentication",
    description = "JWT Token",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {
}
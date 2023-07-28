package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

public record CustomerDto(
		@NotBlank String name,
		@NotBlank String address,
		@NotBlank String cpf,
		@NotBlank String phoneNumber,
		@NotBlank String email
		) {
}

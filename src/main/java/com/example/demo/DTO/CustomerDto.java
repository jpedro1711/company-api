package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CustomerDto(
		@NotBlank String name,
		@NotBlank String address,
		@NotBlank String phoneNumber,
		@NotBlank String email,
		@NotBlank String gender,
		@NotBlank String country,
		@NotBlank String city,
		@NotBlank String creditCardType,
		@NotBlank Integer childrenCount,

		@NotEmpty boolean isMarried,
		@NotNull double salary
		) {
}

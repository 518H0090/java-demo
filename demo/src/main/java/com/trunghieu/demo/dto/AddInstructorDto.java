package com.trunghieu.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddInstructorDto {

    @NotBlank(message = "name must not be empty")
    @Length(min = 4, max = 50, message = "length of name must be between 4 and 50")
    private String name;

    @NotBlank(message = "address must not be empty")
    @Length(min = 4, max = 255, message = "length of address must be between 4 and 255")
    private String address;

    @Pattern(regexp = "^[0-9]{16}$", message = "The phone must be exactly 16 digits")
    private String phone;
}

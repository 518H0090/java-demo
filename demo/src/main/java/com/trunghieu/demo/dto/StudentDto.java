package com.trunghieu.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor(staticName = "build")
@Builder
@NoArgsConstructor
public class StudentDto {

    @NotBlank(message = "id must not be empty")
    private Integer id;

    @NotBlank(message = "name must not be empty")
    @Length(min = 4, max = 30, message = "length of name must be between 4 and 30")
    private String name;

    @NotBlank(message = "address must not be empty")
    @Length(min = 4, max = 255, message = "length of address must be between 4 and 255")
    private String address;

    @Pattern(regexp = "^[0-9]{16}$", message = "The phone must be exactly 16 digits")
    private String phone;
}


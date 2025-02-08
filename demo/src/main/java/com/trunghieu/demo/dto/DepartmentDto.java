package com.trunghieu.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotBlank(message = "code must not be empty")
    private String code;

    @NotBlank(message = "name must not be empty")
    @Length(min = 4, max = 50, message = "length of name must be between 4 and 50")
    private String name;
}

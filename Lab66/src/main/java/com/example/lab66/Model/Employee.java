package com.example.lab66.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {


    // String id , String name , String email , String phoneNumber , int age
    // String position , Boolean onLeave , LocalDate hireDate , annualLeave

    @NotEmpty(message = "id cannot be null")
    @Size(min = 3, message = "id length must be more than 2")
    private String id;

    @NotEmpty(message = "name cannot be null")
    @Size(min = 5, message = "name length must be more than 4")
    @Pattern(regexp = "^[A-Za-z]+$", message = "name must contain only letters")
    private String name;

    @Email(message = "invalid email format")
    @NotEmpty(message = "email cannot be null")
    private String email;

    @Pattern(regexp = "^05\\d{8}$", message = "phone number must start with 05 and be 10 digits long")
    @NotEmpty(message = "phone number cannot be null")
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]+$", message = "must contain only numbers")
    @Min(value = 25 , message = "u must be 25 or older")
    @NotNull
    private int age;

    @NotEmpty
    @Pattern(regexp = "^(supervisor|coordinator)$", message = "role must be either supervisor or coordinator")
    private String position;

    @AssertFalse
    private Boolean onLeave;

    @NotNull
    @PastOrPresent
    private LocalDate hireDate;

    @NotNull
    @Positive
    private int annualLeave;
}

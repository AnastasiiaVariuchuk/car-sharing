package com.example.carsharing.dto.request;

import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String firstName;
    private String lastName;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
}

package com.example.carsharing.dto.request;

import com.example.carsharing.lib.ValidEmail;
import com.example.carsharing.lib.ValidPassword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidPassword
public class UserRequestDto {
    @ValidEmail
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String repeatPassword;
}

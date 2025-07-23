package com.englishacademy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest implements Serializable {

    @NotBlank(message = "{error.name}")
    String name;
    @Email(message = "Invalid email")
    String email;
    String password;
    String role;
    String avatarUrl;
}

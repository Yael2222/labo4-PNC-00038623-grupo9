package com.server.app.dto.user;

import lombok.Data;

@Data
public class UserUpdateDto {

    private String username;

    private String name;

    private String surname;

    private String email;

    private String password;
}
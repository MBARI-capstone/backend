package com.MBARI.dto;

import com.MBARI.entity.Role;
import lombok.Data;


@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String roleName;
    private String username;
    private String password;
}

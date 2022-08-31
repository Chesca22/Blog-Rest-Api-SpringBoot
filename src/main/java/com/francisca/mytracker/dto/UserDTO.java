package com.francisca.mytracker.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String fullName;
    private String email;
    private String password;
}

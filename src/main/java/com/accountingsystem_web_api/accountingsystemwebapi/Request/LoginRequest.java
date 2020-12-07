package com.accountingsystem_web_api.accountingsystemwebapi.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    String username;
    String password;
}
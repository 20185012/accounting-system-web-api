package com.accountingsystem_web_api.accountingsystemwebapi.Request;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String companyCode;
    private String username;
    private String password;
    private String userType;
}

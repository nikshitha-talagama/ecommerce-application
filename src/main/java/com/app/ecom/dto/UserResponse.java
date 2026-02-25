package com.app.ecom.dto;

import lombok.Data;
import com.app.ecom.model.UserRole;
@Data
public class UserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phno;
    private UserRole role;
    private AddressDTO address;
}

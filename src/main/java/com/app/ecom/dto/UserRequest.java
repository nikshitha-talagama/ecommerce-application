package com.app.ecom.dto;

import lombok.Data;

@Data
public class UserRequest {
//    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phno;

    private AddressDTO address;
}

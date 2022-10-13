package kz.galymbay.payintech.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDto {
    private String phoneNumber;
    private String password;
}

package com.exuberant.verifiableCredentialsApp.auth;

import com.exuberant.verifiableCredentialsApp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Integer userid;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}

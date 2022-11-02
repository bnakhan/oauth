package com.oauth.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterRequest {

    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty(message = "email must not be empty")
    @Email
    private String email;

    @NotEmpty(message = "password must not be empty")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

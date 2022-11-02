package com.oauth.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    private String imageUrl;
    private Boolean emailVerified = false;
    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
}

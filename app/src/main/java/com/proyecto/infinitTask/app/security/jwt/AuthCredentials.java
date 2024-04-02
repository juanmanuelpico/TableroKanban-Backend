package com.proyecto.infinitTask.app.security.jwt;

import lombok.Data;

@Data
public class AuthCredentials {
    private String usuario;
    private String password;
}

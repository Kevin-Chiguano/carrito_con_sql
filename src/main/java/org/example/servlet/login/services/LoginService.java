package org.example.servlet.login.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {

    //Obtencion los datos del usuario
    Optional<String> getUserName(HttpServletRequest request);


}

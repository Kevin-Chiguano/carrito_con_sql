package org.example.servlet.login.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

/**
 * Implementación de la interfaz LoginService para obtener el nombre de usuario desde la sesión HTTP.
 */
public class LoginServiceimplment implements LoginService {

    /**
     * Método para obtener el nombre de usuario almacenado en la sesión HTTP.
     *
     * @param request HttpServletRequest que contiene la sesión HTTP.
     * @return Un Optional que contiene el nombre de usuario si está presente en la sesión, o vacío si no lo está.
     */
    @Override
    public Optional<String> getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession(); // Obtiene la sesión HTTP desde la solicitud
        String username = (String) session.getAttribute("username"); // Obtiene el atributo "username" de la sesión

        // Retorna un Optional con el nombre de usuario si no es nulo, de lo contrario, retorna Optional vacío
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }

}

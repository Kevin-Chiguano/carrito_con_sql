package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LogoutServlet") // Anotación para mapear la servlet a la URL "/LogoutServlet"
public class LogoutServlet extends HttpServlet {

    /**
     * Maneja las solicitudes GET para cerrar la sesión del usuario.
     *
     * @param req  el objeto HttpServletRequest que contiene la solicitud del cliente
     * @param resp el objeto HttpServletResponse que contiene la respuesta del servidor
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener la sesión actual del usuario, sin crear una nueva si no existe
        HttpSession session = req.getSession(false);

        // Si existe una sesión, invalidarla
        if (session != null) {
            session.invalidate();
        }

        // Redirigir al usuario a la página de login después de cerrar sesión
        resp.sendRedirect(req.getContextPath() + "/Login");
    }
}

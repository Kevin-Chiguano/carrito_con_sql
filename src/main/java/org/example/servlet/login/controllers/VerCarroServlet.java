package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet para manejar la visualización del carrito de compras.
 */
@WebServlet("/ver-carro")
public class VerCarroServlet extends HttpServlet {

    /**
     * Maneja las solicitudes GET para mostrar la página del carrito de compras.
     *
     * @param req  el objeto HttpServletRequest que contiene la solicitud del cliente
     * @param resp el objeto HttpServletResponse que contiene la respuesta del servidor
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirige la solicitud a la página JSP del carrito de compras
        getServletContext().getRequestDispatcher("/carro.jsp").forward(req, resp);
    }
}

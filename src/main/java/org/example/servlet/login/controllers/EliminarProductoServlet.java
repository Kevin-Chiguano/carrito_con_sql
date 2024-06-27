package org.example.servlet.login.controllers;

// EliminarProductoServlet.java
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/EliminarProducto")
public class EliminarProductoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la conexión de la solicitud
        Connection conn = (Connection) request.getAttribute("conn");

        // Inicializar el servicio de productos con la conexión
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Recuperar el ID del producto a eliminar
        int id = Integer.parseInt(request.getParameter("id"));

        // Eliminar el producto usando el servicio
        service.eliminar(id);

        // Redirigir de vuelta a la página de productos después de eliminar
        response.sendRedirect(request.getContextPath() + "/productos");
    }
}

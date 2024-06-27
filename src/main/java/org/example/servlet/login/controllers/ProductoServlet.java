package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productoServlet", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtenemos la conexión
        Connection conn = (Connection) req.getAttribute("conn");

        // Obtiene el servicio de productos y lista los productos
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        List<Producto> productos = service.listar();

        // Obtiene el servicio de autenticación y verifica el nombre de usuario
        LoginService auth = new LoginServiceimplment();
        Optional<String> usernameOptional = auth.getUserName(req);

        // Establece los atributos para la solicitud
        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);

        // Reenvía la solicitud al JSP
        getServletContext().getRequestDispatcher("/productos.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Manejo de eliminación de producto
        String idProductoStr = req.getParameter("idProducto");
        if (idProductoStr != null && !idProductoStr.isEmpty()) {
            try {
                Integer idProducto = Integer.parseInt(idProductoStr);
                ProductoService service = new ProductoServiceJdbcImplement((Connection) req.getAttribute("conn"));
                service.eliminar(idProducto);
                resp.sendRedirect(req.getContextPath() + "/productos");
                return;
            } catch (NumberFormatException | ServiceJdbcException e) {
                // Manejo de errores
                e.printStackTrace(); // O manejo específico de errores
            }
        }

        // Manejo de edición de producto
        // Redirección al formulario de edición con el ID del producto
        String idEditar = req.getParameter("idEditar");
        if (idEditar != null && !idEditar.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/formulario?idProducto=" + idEditar);
            return;
        }

        // Si ninguna acción válida fue realizada, redirige de vuelta a la lista de productos
        resp.sendRedirect(req.getContextPath() + "/productos");
    }
}

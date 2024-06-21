package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/formulario")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        Integer idProducto;

        try {
            idProducto = Integer.parseInt(req.getParameter("idProducto"));
        } catch (NumberFormatException e) {
            idProducto = 0;
        }

        Producto producto = new Producto();
        producto.setCategoria(new Categoria());

        if (idProducto > 0) {
            Optional<Producto> o = service.porId(idProducto);
            if (o.isPresent()) {
                producto = o.get();
            }
        }

        req.setAttribute("categorias", service.listarCategorias());
        req.setAttribute("producto", producto);
        getServletContext().getRequestDispatcher("/nuevoProducto.jsp").forward(req, resp);
    }
}

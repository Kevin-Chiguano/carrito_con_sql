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
import java.util.HashMap;
import java.util.Map;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Obtener parámetros del formulario
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String strPrecio = req.getParameter("precio");
        String strIdCategoria = req.getParameter("categoria");
        String strIdProducto = req.getParameter("idProducto");

        // Validar y convertir parámetros
        Map<String, String> errores = validarParametros(nombre, descripcion, strPrecio, strIdCategoria);
        Integer idCategoria = convertirAInteger(strIdCategoria);
        Double precio = convertirADouble(strPrecio);
        Integer idProducto = convertirAInteger(strIdProducto);

        // Crear objeto Producto
        Producto producto = new Producto();
        producto.setId(idProducto);
        producto.setNombre(nombre);
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        producto.setCategoria(categoria);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);

        // Guardar o mostrar errores
        if (errores.isEmpty()) {
            service.guardar(producto);
            resp.sendRedirect("productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategorias());
            req.setAttribute("producto", producto);
            getServletContext().getRequestDispatcher("/nuevoProducto.jsp").forward(req, resp);
        }
    }

// Métodos auxiliares para validar y convertir parámetros

    private Map<String, String> validarParametros(String nombre, String descripcion, String strPrecio, String strIdCategoria) {
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es obligatorio");
        }
        if (descripcion == null || descripcion.isBlank()) {
            errores.put("descripcion", "La descripción es obligatoria");
        }
        // Validar otros campos según sea necesario
        return errores;
    }

    private Integer convertirAInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0; // o manejar según tu lógica
        }
    }

    private Double convertirADouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.0; // o manejar según tu lógica
        }
    }




}

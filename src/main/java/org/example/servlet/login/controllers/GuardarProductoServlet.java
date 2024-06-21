package org.example.servlet.login.controllers;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/GuardarProductoServlet")
public class GuardarProductoServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        int idCategoria = Integer.parseInt(request.getParameter("categoria")); // Aquí obtienes el ID de la categoría seleccionada
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "INSERT INTO producto (nombre, idcategoria, descripcion, precio) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setInt(2, idCategoria);
            stmt.setString(3, descripcion);
            stmt.setDouble(4, precio);

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                // Éxito al insertar el producto
                response.sendRedirect(request.getContextPath() + "/index.html");
            } else {
                // Error al insertar el producto
                response.sendRedirect(request.getContextPath() + "/nuevoProducto.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
            response.sendRedirect(request.getContextPath() + "/nuevoProducto.jsp");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

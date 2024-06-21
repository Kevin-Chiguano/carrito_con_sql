package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.models.Categoria;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/nuevoProducto")
public class NuevoProductoServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> categorias = obtenerCategorias();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/nuevoProducto.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para guardar el nuevo producto
    }

    private List<Categoria> obtenerCategorias() throws ServletException {
        List<Categoria> categorias = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT idcategoria, nombre FROM categoria WHERE condicion = 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCategoria = rs.getInt("idcategoria");
                String nombre = rs.getString("nombre");

                Categoria categoria = new Categoria(idCategoria, nombre);
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            throw new ServletException("Error en la conexión a la base de datos", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categorias;
    }
}

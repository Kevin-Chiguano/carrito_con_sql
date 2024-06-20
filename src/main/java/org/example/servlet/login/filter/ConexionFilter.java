package org.example.servlet.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.services.ServiceJdbcException;
import util.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Filtro para gestionar la conexión a la base de datos.
 */
@WebFilter("/*")
public class ConexionFilter implements Filter {

    /**
     * Método principal del filtro que maneja las solicitudes.
     *
     * @param request  el objeto ServletRequest que contiene la solicitud del cliente
     * @param response el objeto ServletResponse que contiene la respuesta del servidor
     * @param chain    el objeto FilterChain que permite pasar la solicitud al siguiente filtro
     * @throws IOException      si ocurre un error de E/S
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;


        try (Connection conn = Conexion.getConnection()) {
            // Configura la conexión para no hacer autocommit por defecto
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                // Establece la conexión como atributo del request para que esté disponible en el servlet
                request.setAttribute("conn", conn);
                chain.doFilter(request, response); // Pasa la solicitud al siguiente filtro o servlet

                // Realiza commit si no hay excepciones
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback(); // Hace rollback si ocurre una excepción SQL
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException throwables) {
            // Manejo de excepciones de conexión
            throwables.printStackTrace();
        }
    }


}

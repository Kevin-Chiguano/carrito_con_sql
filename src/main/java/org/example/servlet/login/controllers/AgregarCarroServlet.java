package org.example.servlet.login.controllers;

//import com.mysql.cj.Session; // Importación incorrecta, puede eliminarse si no se utiliza
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlet.login.models.Carro;
import org.example.servlet.login.models.ItemCarro;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceImplement;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/agregar-carro") // Anotación para mapear la servlet a la URL "/agregar-carro"
public class AgregarCarroServlet extends HttpServlet {

    /**
     * Maneja las solicitudes GET para agregar un producto al carro de compras.
     *
     * @param req  el objeto HttpServletRequest que contiene la solicitud del cliente
     * @param resp el objeto HttpServletResponse que contiene la respuesta del servidor
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener el parámetro 'id' de la solicitud y convertirlo a Integer
        Integer id = Integer.parseInt(req.getParameter("id"));
        Connection conn = (Connection)req.getAttribute("conn");
        // Crear una instancia del servicio de productos e intentar obtener el producto por su ID
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        Optional<Producto> producto = service.porId(id);

        // Si el producto está presente, añadirlo al carro de compras
        if (producto.isPresent()) {
            // Crear un nuevo ItemCarro con cantidad 1 y el producto obtenido
            ItemCarro item = new ItemCarro(1, producto.get());

            // Obtener la sesión actual del usuario
            HttpSession session = req.getSession();

            // Intentar obtener el carro de la sesión, o crear uno nuevo si no existe
            Carro carro;
            if (session.getAttribute("carro") == null) {
                carro = new Carro();
                session.setAttribute("carro", carro);
            } else {
                carro = (Carro) session.getAttribute("carro");
            }

            // Añadir el item al carro
            carro.addItem(item);
        }

        // Redirigir al usuario a la página de visualización del carro de compras
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}

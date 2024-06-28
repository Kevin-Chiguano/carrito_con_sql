package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlet.login.services.LoginService;
import org.example.servlet.login.services.LoginServiceimplment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * Servlet para manejar el proceso de inicio de sesión.
 */
@WebServlet({"/Login", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "1234";

    /**
     * Maneja las solicitudes GET para verificar si el usuario ya ha iniciado sesión.
     *
     * @param req  el objeto HttpServletRequest que contiene la solicitud del cliente
     * @param resp el objeto HttpServletResponse que contiene la respuesta del servidor
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceimplment();
        Optional<String> usernameOptional = auth.getUserName(req);

        if (usernameOptional.isPresent()) {
            // El usuario ha iniciado sesión, se muestra un mensaje de bienvenida
            HttpSession session = req.getSession();
            String role = (String) session.getAttribute("role");
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ServletLogin</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
                out.println("h1 { background-color: #4CAF50; color: white; padding: 20px; text-align: center; }");
                out.println("p { margin: 20px; font-size: 18px; text-align: center; }");
                out.println("a { color: #007BFF; text-decoration: none; }");
                out.println("a:hover { text-decoration: underline; }");
                out.println("button { background-color: #007BFF; color: white; padding: 10px 20px; border: none; cursor: pointer; border-radius: 5px; }");
                out.println("button:hover { background-color: #0056b3; }");
                out.println(".container { text-align: center; margin: 20px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Login</h1>");
                out.println("<div class='container'>");
                out.println("<p>Hola <strong>" + usernameOptional.get() + "</strong>, has iniciado sesión con éxito como <strong>" + role + "</strong>.</p>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/LogoutServlet'><button>Cerrar Sesión</button></a></p>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // El usuario no ha iniciado sesión, se redirige a la página de inicio de sesión
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    /**
     * Maneja las solicitudes POST para procesar el formulario de inicio de sesión.
     *
     * @param req  el objeto HttpServletRequest que contiene la solicitud del cliente
     * @param resp el objeto HttpServletResponse que contiene la respuesta del servidor
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException      si ocurre un error de E/S
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtiene los parámetros de usuario y contraseña del formulario de inicio de sesión
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Verifica las credenciales y asigna el rol correspondiente
        HttpSession session = req.getSession();
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            // Credenciales de administrador correctas
            session.setAttribute("username", username);
            session.setAttribute("role", "admin");
            resp.sendRedirect(req.getContextPath() + "/ServletLogin");
        } else if (USER_USERNAME.equals(username) && USER_PASSWORD.equals(password)) {
            // Credenciales de usuario correctas
            session.setAttribute("username", username);
            session.setAttribute("role", "user");
            resp.sendRedirect(req.getContextPath() + "/ServletLogin");
        } else {
            // Credenciales incorrectas, se envía un error de autorización
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no está autorizado para ingresar al sistema");
        }
    }
}

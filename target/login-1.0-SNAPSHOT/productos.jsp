<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.servlet.login.models.Producto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Listado de Productos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }
        h1 { background-color: #4CAF50; color: white; padding: 20px; text-align: center; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        tr:hover { background-color: #ddd; }
        .navbar { text-align: center; margin-bottom: 20px; }
        .navbar a { margin: 0 15px; text-decoration: none; color: #4CAF50; }
        .add-to-cart { text-align: center; }
        .add-to-cart a { display: inline-block; padding: 10px 20px; border: none; background-color: #007BFF; color: #fff; cursor: pointer; border-radius: 5px; text-decoration: none; }
        .add-to-cart a:hover { background-color: #0056b3; }
        .add-product-btn { text-align: center; margin-bottom: 20px; }
        .add-product-btn a { padding: 10px 20px; border: none; background-color: #28a745; color: #fff; cursor: pointer; border-radius: 5px; text-decoration: none; }
        .add-product-btn a:hover { background-color: #218838; }
        .edit-btn { background-color: #ffc107; color: #fff; padding: 5px 10px; text-decoration: none; border-radius: 5px; }
        .edit-btn:hover { background-color: #e0a800; }
        .delete-btn { background-color: #dc3545; color: #fff; padding: 5px 10px; border: none; border-radius: 5px; cursor: pointer; }
        .delete-btn:hover { background-color: #c82333; }
    </style>
</head>
<body>
<div class="navbar">
    <a href="${pageContext.request.contextPath}/index.html">Inicio</a>
    <a href="${pageContext.request.contextPath}/LogoutServlet">Salir</a>
</div>
<h1>Listado de productos</h1>
<div>
    <%

        String username = null;
        String role = null;
        if (session != null) {
            username = (String) session.getAttribute("username");
            role = (String) session.getAttribute("role");
        }
        if (username != null) {
            out.println("HOLA <span>" + username + "</span>, BIENVENIDO!");
        }
    %>
</div>
<table>
    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>categoria</th>
        <th>descripcion</th>
        <% if (username != null) { %>
        <th>precio</th>
        <th>Acción</th>
        <% } %>
    </tr>
    <%
        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
        for (Producto p : productos) {
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNombre() %></td>
        <td><%= p.getCategoria().getNombre() %></td>
        <td><%= p.getDescripcion() %></td>
        <% if (username != null) { %>
        <td><%= p.getPrecio() %></td>
        <td class="add-to-cart"><a href="<%= request.getContextPath() + "/agregar-carro?id=" + p.getId() %>">Agregar al carro</a></td>
        <% if ("admin".equals(role)) { %>
        <td>
            <a href="<%= request.getContextPath() + "/formulario?idProducto=" + p.getId() %>" class="edit-btn">Editar</a>
            <form method="post" action="<%= request.getContextPath() + "/productos" %>" style="display:inline;">
                <input type="hidden" name="idProducto" value="<%= p.getId() %>">
                <button type="submit" class="delete-btn">Eliminar</button>
            </form>
        </td>
        <% } %>
        <% } %>
    </tr>
    <% } %>
</table>

<% if ("admin".equals(role)) { %>
<div class="add-product-btn">
    <a href="<%= request.getContextPath()%>/formulario">Agregar Nuevo Producto</a>
</div>
<% } %>
<div style="text-align: center;">
    <button onclick="window.location.href='<%= request.getContextPath() + "/index.html" %>'">Regresar</button>
</div>
</body>
</html>

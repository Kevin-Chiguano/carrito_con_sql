<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.servlet.login.models.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.example.servlet.login.models.Producto" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Producto producto = (Producto) request.getAttribute("producto");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Producto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
        }
        input[type="text"], input[type="number"], select {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            margin-top: 20px;
            padding: 10px;
            border: none;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
        }
        .back-link {
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Agregar Nuevo Producto</h1>
    <form action="formulario" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= producto != null ? producto.getNombre() : "" %>" required>
        <% if (errores != null && errores.containsKey("nombre")) { %>
        <div class="error"><%= errores.get("nombre") %></div>
        <% } %>

        <label for="categoria">Categoría:</label>
        <select id="categoria" name="categoria" required>
            <%
                if (categorias != null && !categorias.isEmpty()) {
                    for (Categoria categoria : categorias) {
            %>
            <option value="<%= categoria.getIdCategoria() %>"
                    <%= producto != null && producto.getCategoria().getIdCategoria() == categoria.getIdCategoria() ? "selected" : "" %>>
                <%= categoria.getNombre() %></option>
            <%
                }
            } else {
            %>
            <option value="">No hay categorías disponibles</option>
            <%
                }
            %>
        </select>
        <% if (errores != null && errores.containsKey("idCategoria")) { %>
        <div class="error"><%= errores.get("idCategoria") %></div>
        <% } %>

        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" value="<%= producto != null ? producto.getDescripcion() : "" %>" required>
        <% if (errores != null && errores.containsKey("descripcion")) { %>
        <div class="error"><%= errores.get("descripcion") %></div>
        <% } %>

        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" step="0.01" value="<%= producto != null ? producto.getPrecio() : "" %>" required>
        <% if (errores != null && errores.containsKey("precio")) { %>
        <div class="error"><%= errores.get("precio") %></div>
        <% } %>

        <input type="submit" value="<%= (producto != null && producto.getId() != null && producto.getId() > 0) ? "Editar" : "Crear" %>">

        <input type="hidden" name="idProducto" value="<%= producto != null ? producto.getId() : "" %>">
    </form>
    <div class="back-link">
        <a href="productos">Regresar al listado de productos</a>
    </div>
</div>
</body>
</html>
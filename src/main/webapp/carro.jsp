<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 13/6/2024
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.example.servlet.login.models.*" %>
<%Carro carro = (Carro) session.getAttribute("carro"); %>
<html>
<head>
    <title>Carro de Compras</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }
        h1 { background-color: #4CAF50; color: white; padding: 20px; text-align: center; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        tr:hover { background-color: #ddd; }
        .message { text-align: center; margin: 20px; font-size: 18px; }
        .actions { text-align: center; margin: 20px; }
        .actions a, .actions button { padding: 10px 20px; border: none; background-color: #007BFF; color: #fff; cursor: pointer; border-radius: 5px; text-decoration: none; }
        .actions a:hover, .actions button:hover { background-color: #0056b3; }
        .total-row { font-weight: bold; }
    </style>
</head>
<body>
<h1>Carro de Compras</h1>
<% if (carro == null || carro.getItems().isEmpty()) { %>
<p class="message">Lo sentimos, no hay productos en el carro de compras!</p>
<% } else { %>
<table>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Precio</th>
        <th>Cantidad</th>
        <th>Subtotal</th>
    </tr>
    <% for (ItemCarro item : carro.getItems()) { %>
    <tr>
        <td><%= item.getProducto().getId() %></td>
        <td><%= item.getProducto().getNombre() %></td>
        <td><%= item.getProducto().getPrecio() %></td>
        <td><%= item.getCantidad() %></td>
        <td><%= item.getImporte() %></td>
    </tr>
    <% } %>
    <tr class="total-row">
        <td colspan="4">Total:</td>
        <td><%= carro.getTotal() %></td>
    </tr>
</table>
<% } %>
<div class="actions">
    <a href="<%= request.getContextPath() %>/productos">Seguir Comprando</a>
    <a href="<%= request.getContextPath() %>/index.html">Volver</a>
</div>
</body>
</html>

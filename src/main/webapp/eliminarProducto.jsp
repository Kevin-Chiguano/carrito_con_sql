<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 27/6/2024
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Producto</title>
</head>
<body>
<h1>Eliminar Producto</h1>
<form method="post" action="<%= request.getContextPath() + "/productos" %>">
    <p>¿Estás seguro de que quieres eliminar este producto?</p>
    <input type="hidden" name="idProducto" value="<%= request.getParameter("idProducto") %>">
    <button type="submit">Eliminar</button>
    <a href="<%= request.getContextPath() + "/productos" %>">Cancelar</a>
</form>
</body>
</html>

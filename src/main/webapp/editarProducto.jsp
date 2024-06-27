<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 27/6/2024
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>

<!-- editarProducto.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Producto</title>
</head>
<body>
<h1>Editar Producto</h1>
<form action="${pageContext.request.contextPath}/EditarProducto" method="post">
    <input type="hidden" name="id" value="${producto.id}" />
    Código: <input type="text" name="codigo" value="${producto.codigo}" /><br />
    Nombre: <input type="text" name="nombre" value="${producto.nombre}" /><br />
    Stock: <input type="number" name="stock" value="${producto.stock}" /><br />
    Categoría:
    <select name="categoria">
        <c:forEach items="${categorias}" var="categoria">
            <option value="${categoria.id}" ${categoria.id == producto.categoria.id ? 'selected' : ''}>${categoria.nombre}</option>
        </c:forEach>
    </select><br />
    Descripción: <textarea name="descripcion">${producto.descripcion}</textarea><br />
    Imagen: <input type="text" name="imagen" value="${producto.imagen}" /><br />
    Condición: <input type="number" name="condicion" value="${producto.condicion}" /><br />
    Precio: <input type="text" name="precio" value="${producto.precio}" /><br />
    <input type="submit" value="Guardar" />
</form>
<c:if test="${not empty errores}">
    <ul>
        <c:forEach items="${errores}" var="error">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>

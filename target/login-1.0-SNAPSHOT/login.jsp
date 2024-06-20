<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 30/5/2024
  Time: 8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iniciar Sesión</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .container h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .container div {
            margin-bottom: 15px;
        }
        .container label {
            display: block;
            margin-bottom: 5px;
        }
        .container input[type="text"],
        .container input[type="password"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .container input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Iniciar Sesión</h1>
    <form action="Login" method="post">
        <div>
            <label for="username">Ingrese nombre de usuario</label>
            <input type="text" name="username" id="username" required>
        </div>
        <div>
            <label for="password">Ingrese contraseña</label>
            <input type="password" name="password" id="password" required>
        </div>
        <div>
            <a href="index.html">Regresar al inicio</a>
            <input type="submit" value="Login">
        </div>
    </form>
</div>
</body>
</html>

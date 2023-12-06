<!-- 
+-----------+
|           |
| index.jsp |
|           |
+-----------+ 
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Veterinaria</title>
        
        <link rel="stylesheet" type="text/css" href="resources/css/styles.css">
    </head>
    <body>
        
        <div class="capa">
            <form method="POST" action="/validarUsuario">
                <h1>Bienvenidos a la Veterinaria</h1>
                
                <div class="form-content">
                    <label for="exampleInputEmail1">Usuario: </label>
                    <input type="text" aria-describedby="emailHelp" name="nombre" required>
                </div>
                <div class="form-content">
                    <label for="exampleInputEmail1">Contraseña: </label>
                    <input type="password" aria-describedby="emailHelp" name="clave" required>
                </div>
                
                <button type="submit" value="Iniciar sesion" class="boton">Log In</button>
            </form>
        </div>
        <!--
        <div class="center">
            <form method="post" action="/validarUsuario">
                <div class="mb-3">
                    <h1>Bienvenidos a la veterinaria</h1>
                </div>

                <div class="mb-3">
                    <div class="centrarBox">
                        <label id="bloqueMail" for="exampleInputEmail1" class="form-label">Usuario</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="nombre" required>
                        <c:choose>
                            <c:when test="${not empty usuario}">
                                <div id="emailHelp" class="form-text">Nombre o contraseña incorrectas.</div>
                            </c:when>
                        </c:choose>

                    </div>
                </div>

                <div class="mb-3">
                    <div class="centrarBox">
                        <label for="exampleInputPassword1" class="form-label">Clave</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" name="clave" required>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary" value="Iniciar sesion">Log In</button>
            </form>
        </div>
        -->
    </body>
</html>
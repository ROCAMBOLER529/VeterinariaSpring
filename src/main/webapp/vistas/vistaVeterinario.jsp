<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Veterinaria</title>

        <link rel="stylesheet" type="text/css" href="resources/css/veterinario.css">
    </head>
    <body>
        <h1>Hola ${usuario.nombre}</h1>
        <div class="main">
            <div>
                <form id="atenderFORM" method="POST" action="/tomarTurno">
                    <h3>Atender un turno</h3>                    
                    <select name="verTurno" size="1">
                        <c:forEach var="verTurno" items="${turnosRecep}">
                            <option value="${verTurno.id_turnos}">  ${verTurno.id_turnos} ${verTurno.dia} ${verTurno.hora} ${verTurno.especialidad} </option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="boton">Atender turno</button>
                </form>
            </div>
            <div>
                <form id="venderFORM" method="POST" action="/ventaProdVet">
                    <h3>Vender un producto</h3>
                    <select name="prod">
                        <c:forEach var="prod" items="${productos}">
                            <option value="${prod.id_productos}">${prod.descripcion}: $ ${prod.precio}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="boton">Vender producto</button>
                </form>
            </div>
        </div>
        <input class="volver" type="button" value="Log Out" name="B4" OnClick="location.href= 'vistas/index.jsp' " >
    </body>
</html>
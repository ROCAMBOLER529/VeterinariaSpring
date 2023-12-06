<!--
+------------------------+
|                        |
| vistaRecepcionista.jsp |
|                        |
+------------------------+
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="resources/css/recepcionista.css">
        <title>Recepcionista</title>
    </head>
    <body>
        <h1>Hola ${usuario.nombre}</h1>
        
        <div class="main">
            <div>
                <form method="post" action="/altaTurnos" id="altaTurnos">
                    <h1>Anotar turno</h1>
                    <div>
                        <h3>Nombre de la mascota</h3>
                        <input type="text" name="nom_mascota" required>
                    </div>
                    <div>
                        <h3>Raza</h3>
                        <select name="especialidad" size="1">
                            <option value="gato"> gato </option>
                            <option value="perro"> perro </option>
                            <option value="tortuga y/o canario"> tortuga y/o canario </option>
                        </select>
                    </div>
                    <div>
                        <h3>Día</h3>
                        <select name="dia" size="1">
                            <option value="Lunes"> Lunes </option>
                            <option value="Martes"> Martes </option>
                            <option value="Miércoles">Miércoles </option>
                            <option value="Jueves">Jueves </option>
                            <option value="Viernes">Viernes </option>
                            <option value="Sábado">Sábado </option>
                        </select>
                    </div>
                    <div>
                        <h3>Hora</h3>
                        <select name="hora" size="1">
                            <option value="9:00"> 9:00 </option>
                            <option value="9:30"> 9:30 </option>
                            <option value="10:00">10:00</option>                                                   
                            <option value="10:30">10:30</option>                                                   
                            <option value="11:00">11:00</option>                                                   
                            <option value="11:30">11:30</option>                                                     
                            <option value="12:00">12:00</option>
                            <option value="13:30">13:30</option>                                                     
                            <option value="14:00">14:00</option>
                            <option value="14:30">14:30</option>                                                     
                            <option value="15:00">15:00</option>
                            <option value="15:30">15:30</option>                                                     
                            <option value="16:00">16:00</option>
                            <option value="16:30">16:30</option>                                                     
                            <option value="17:00">17:00</option>
                            <option value="17:30">17:30</option>
                        </select>
                    </div>
                    <div>
                        <h3>Nombre del dueño</h3>
                        <input type="text" name="nom_dueño" required>
                    </div>
                    <div>
                        <h3>Tel.</h3>
                        <input type="text" name="contacto" required>
                    </div>
                    
                    <button type="submit">Agregar Turno</button>
                </form>
            </div>
            <div>
                <table>
                    <h1>Lista de turnos anotados</h1>
                    <tr>
                        <th>Día</th>
                        <th>Hora</th>
                        <th>Nombre (mascota)</th>
                        <th>Especialidad</th>
                    </tr>
                    <c:forEach var="verTurno" items="${turnosRecep}" >
                        <tr>
                            <td>${verTurno.dia}</td>
                            <td>${verTurno.hora}</td>
                            <td>${verTurno.nom_dueño}(${verTurno.nom_mascota})</td>
                            <td>${verTurno.especialidad}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div>
                <form method="post" action="/ventaProdRecep">
                    <h1>Vender producto</h1>         
                    <select name="prod" size="1">
                        <c:forEach var="prod" items="${productoRecep}" >
                        <option value="${prod.id_productos}"> ${prod.descripcion}  ${prod.categoria} ${prod.precio}</option>                                
                        </c:forEach>
                    </select>
                    
                    <button type="submit">Vender</button>
                </form>
            </div>
        </div>
        <input class="volver" type="button" value="Log Out" name="B4" OnClick="location.href= 'vistas/index.jsp' " >
    </body>
</html>

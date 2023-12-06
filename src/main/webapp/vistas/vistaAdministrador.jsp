<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Veterinaria</title>

        <link rel="stylesheet" type="text/css" href="resources/css/administrador.css">
    </head>
    <body>
        <h1>Hola ${usuario.nombre}</h1>

        <div class="main">
            <div id="usuarios">
                <div style="background-color: rgb(255, 127, 127)">
                    <form method="POST" action="/altaUsuario" id="altaUsuario">
                        <h3>Dar de alta a Usuario</h3>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Nombre: </label>
                            <input type="text" name="nombre" required>  
                        </div>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Contraseña: </label>
                            <input type="password" name="clave" required> 
                        </div>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Tipo: </label>
                            <select name="tipo">
                                <option value="veterinario">Veterinario</option>
                                <option value="recepcionista">Recepcionista</option>
                                <option value="administrador">Administrador</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Dar de alta</button>
                    </form>
                </div>
                <div style="background-color: rgb(255, 127, 127)">
                    <form method="POST" action="/bajaUsuario">
                        <h3>Dar de baja a Usuario</h3>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Seleccione un usuario: </label>
                            <select name="usuario_id" size="1">
                                <c:forEach var="usuario_id" items="${usuarios}">
                                    <option value="${usuario_id.usuario_id}">${usuario_id.usuario_id} ${usuario_id.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Dar de baja</button>
                    </form>
                </div>
            </div>
            <div id="vets">
                <div style="background-color: rgb(127, 255, 127)">
                    <form method="POST" action="/altaVeterinario">
                        <h3>Habilitar Veterinario</h3>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Seleccione el vet: </label>
                            <select name="usuario_id">
                                <c:forEach var="usuario_id" items="${veterinarios}">
                                    <option value="${usuario_id.usuario_id}">${usuario_id.usuario_id} ${usuario_id.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Especializacion: </label>
                            <select name="especialidad">
                                <option value="perro">Perro</option>
                                <option value="gato">Gato</option>
                                <option value="tortuga y/o canario">Tortuga y/o Canario</option>
                            </select>
                        </div>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Dias disponibles: </label>
                            <select name="dia1">
                                <option value="lunes">Lunes</option>
                                <option value="martes">Martes</option>
                                <option value="miercoles">Miercoles</option>
                                <option value="jueves">Jueves</option>
                                <option value="viernes">Viernes</option>
                                <option value="sabado">Sabado</option>
                            </select>
                            <select name="dia2">
                                <option value="lunes">Lunes</option>
                                <option value="martes">Martes</option>
                                <option value="miercoles">Miercoles</option>
                                <option value="jueves">Jueves</option>
                                <option value="viernes">Viernes</option>
                                <option value="sabado">Sabado</option>
                            </select>
                            <select name="dia3">
                                <option value="lunes">Lunes</option>
                                <option value="martes">Martes</option>
                                <option value="miercoles">Miercoles</option>
                                <option value="jueves">Jueves</option>
                                <option value="viernes">Viernes</option>
                                <option value="sabado">Sabado</option>
                            </select>
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Asignar</button>
                    </form>
                </div>
                <div style="background-color: rgb(127, 255, 127)">
                    <form method="POST" action="/bajaVeterinario">
                        <h3>Deshabilitar Veterinario</h3>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Seleccione un vet: </label>
                            <select name="veterinario_id">
                                <c:forEach var="veterinario_id" items="${veterinariosL}">
                                    <option value="${veterinario_id.veterinario_id}">${veterinario_id.veterinario_id} ${veterinario_id.nombre}</option> 
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Deshabilitar</button>
                    </form>
                </div>
            </div>
            <div id="productos">
                <div style="background-color: rgb(127, 127, 255)">
                    <form method="POST" action="/altaProductos">
                        <h3>Alta de producto</h3>
                        <div class="form-content">
                            <label for="exampleInputEmail1">Nombre del producto</label>
                            <input type="text" name="descripcion" required> 
                        </div>
                        <div class="form-content">
                            <label for="exampleInputPassword1">Precio</label>
                            <input type="number" name="precio" required>
                        </div>
                        <div class="form-content">
                            <label for="exampleInputPassword1">Precio</label>
                            <select name="categoria">
                                <option value="regular">Regular</option>
                                <option value="medicamentos">Medicamentos</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Subir producto</button>
                    </form>
                </div>
                <div style="background-color: rgb(127, 127, 255)">
                    <form method="POST" action="/modificarVeterinario">
                        <h3>Modificar dias de Veterinario</h3>
                        <div class="form-content">
                            <label for="exampleInputPassword1">Seleccione veterinario</label>
                            <select name="veterinario_id">
                                <c:forEach var="veterinario_id" items="${veterinariosL}">
                                    <option value="${veterinario_id.veterinario_id}">${veterinario_id.veterinario_id} ${veterinario_id.nombre}</option> 
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-content">
                            <label for="exampleInputPassword1">Seleccione los nuevos días</label>
                            <select name="dia1">
                                <option value="lunes">Lunes</option>
                                <option value="martes">Martes</option>
                                <option value="miercoles">Miercoles</option>
                                <option value="jueves">Jueves</option>
                                <option value="viernes">Viernes</option>
                                <option value="sabado">Sabado</option>
                            </select>
                            <select name="dia2">
                                <option value="lunes">Lunes</option>
                                <option value="martes">Martes</option>
                                <option value="miercoles">Miercoles</option>
                                <option value="jueves">Jueves</option>
                                <option value="viernes">Viernes</option>
                                <option value="sabado">Sabado</option>
                            </select>
                            <select name="dia3">
                                <option value="lunes">Lunes</option>
                                <option value="martes">Martes</option>
                                <option value="miercoles">Miercoles</option>
                                <option value="jueves">Jueves</option>
                                <option value="viernes">Viernes</option>
                                <option value="sabado">Sabado</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Subir producto</button>
                    </form>
                </div>
            </div>
        </div>
        
        <a href="vistas/index.jsp">
            <input class="volver" type="button" value="Log Out">
        </a>
    </body>
</html>
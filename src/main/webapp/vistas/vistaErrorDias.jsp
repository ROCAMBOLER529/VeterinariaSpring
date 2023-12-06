<%-- 
    Document   : vistaErrorDias
    Created on : 21 feb. 2023, 19:58:49
    Author     : Ignacio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Error Usuario</title>
        
        <link rel="stylesheet" type="text/css" href="resources/css/estilos.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    </head>
    <body>
        <h1>Error en la carga de los días</h1>
        
        <input type="button" value="Página anterior" name="B4" OnClick="location.href= 'vistas/vistaAdmnistrador.jsp' " >  
        <input type="button" value="Inicio" name="B4" OnClick="location.href= 'vistas/index.jsp' " > 
    </body>
</html>

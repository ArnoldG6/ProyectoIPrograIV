<%-- 
    Document   : R1
    Created on : Apr 14, 2021, 3:14:19 PM
    Author     : arnol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Búsqueda de Cursos de la Universidad</title>
    </head>
    <body>
        <div>
            <form action="consultarMVC" method="POST">
            Curso:<input type="text" name="placa">
            <input type="submit" value="Consultar">
            </form>
        </div>
    </body>
</html>

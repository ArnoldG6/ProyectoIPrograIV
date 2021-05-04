<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<% User user = (User) session.getAttribute("user");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de cursos</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white">

        <div align='center' class="w-200 p-10 bg-dark">
            <form name = "form" class = "container center_div w-75 p-3" action="#" method="post">
                <input  class = "container center_div w-75 p-1 text-center" type="search" name="busquedacursos" placeholder="Buscar cursos" >
                <div>&nbsp;</div>
                <input  type="submit" class = "container center_div w-25 p-1" value="Buscar">
            </form> 
        </div>

        <div class="container">
            <h1 class="text-white text-center">Cursos registrados en el sistema</h1>
        </div>
    </body>
</html>

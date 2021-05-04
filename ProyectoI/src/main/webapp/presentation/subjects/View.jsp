<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<% User user = (User) session.getAttribute("user");%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.awt.Image"%>
<%@page import="proyecto.model.Subject"%>
 <%HashMap<String,Subject> subjects = (HashMap<String,Subject>)session.getAttribute("subjects"); %>

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
            <% if (subjects != null) { %>
            <h1 class="text-white text-center">Cursos registrados en el sistema</h1>
                <%for(Map.Entry<String, Subject> entry : subjects.entrySet()) { %>   
                    <% Subject sub = subjects.get(entry.getKey()); %>
                    <div class="card p-3 bg-dark col ">
                        <a href="#" id="imagen1" class="p-3 bg-dark " >
                            <img class="card-img-top" src="/ProyectoI/images/Buda.jpg" height="100" width = "10" alt="Card image cap" >
                        </a>
                    <div class="card-body">
                        <label class="card-title"><%= sub.getIdSub()%></label>
                        <div>&nbsp;</div>
                        <label class="card-title"><%= sub.getNameSubj()%></label>
                        <div>&nbsp;</div>
                        <label class="card-title"><%= sub.getDesc()%></label>
                        <a class ="btn btn-outline-light row justify-content-center" href="#">Matricular ahora</a>
                    </div>
                </div>   
                <% } %>
                <% session.removeAttribute("subjects"); %>
            <% } %>
        </div>
    </body>
</html>

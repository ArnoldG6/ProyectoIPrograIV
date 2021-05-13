<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.awt.Image"%>
<%@page import="proyecto.model.Subject"%>
<% User user = (User) session.getAttribute("user");%>
<%HashMap<String, Subject> subjects = (HashMap<String, Subject>) session.getAttribute("subjects"); %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="/ProyectoI/images/landing-page.png" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de cursos</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white">

        <div align='center' class="w-200 p-10 bg-dark">
            <form name = "form" class = "container center_div w-75 p-3" action="/ProyectoI/presentation/subjects/search" method="post">
                <input  class = "container center_div w-75 p-1 text-center" type="text" name="searchCriteria" id = "searchCriteria" placeholder="Nombre, código o descripción del curso" >
                <div>&nbsp;</div>
                <button class ="btn btn-outline-light form-control w-25" type = "submit">Buscar curso</button>
                <!-- -->
            </form> 
        </div>

        <div class="container">
            
            <% if (subjects != null) { %>
            <h1 class="text-white text-center">Cursos registrados en el sistema</h1>
            <div class="row p-1 bg-dark column card-body w-15">
            <%for (Map.Entry<String, Subject> entry : subjects.entrySet()) { %>   
            <% Subject sub = subjects.get(entry.getKey());%>

            
                <div class="card p-3 bg-dark col w-15">
                    <a href="#" id="imagen1" class="p-3 bg-dark text-center" >
                        <img src='/ProyectoI/presentation/subjects/image?subId=<%=sub.getIdSub()%>' height="150" width = "150">
                    </a>
                    <div><label class="card-title text-center"><%= sub.getNameSubj()%></label></div>
                    <div>&nbsp;</div>
                    <div><label class="text-center"><%= sub.getIdSub()%></label></div>
                    <div>&nbsp;</div>
                    <div><label class="text-center"><%= sub.getDesc()%></label></div>
                        <% if (user == null) {%>
                    <div><a class ="btn btn-outline-light container center_div w-75 p-1 " href="#">Matricular ahora</a></div>
                    <%} else {%>
                    <% if (user.getType() != 3) {%>
                    <div><a class ="btn btn-outline-light container center_div w-75 p-1" href="#">Matricular ahora</a></div>
                    <%}%>
                    <%}%>
                </div>
            

            <% } %>
    
            </div>
            <% }else { %>
            <div class="text-white text-center"></div>
            <% }%>
        </div>
    </body>
</html>

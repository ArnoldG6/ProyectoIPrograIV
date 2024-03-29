<%@page import="java.util.Map"%>
<%@page import="proyecto.model.Subject"%>
<%@page import="proyecto.model.Group"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="javafx.util.Pair"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Group> groups = (List<Group>) session.getAttribute("groups"); %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="/ProyectoI/images/landing-page.png" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial de cursos</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <jsp:include page="/presentation/Header.jsp"/>
        <h1 class = "text-center">Historial de cursos</h1> 
        <div class="container">
            <% if (groups != null) { %>
            <div class="card-body bg-dark text-white">
                <table class = "table table-condensed border">
                    <tr>
                        <th class="text-center text-white">NRC del curso</th>
                        <th class="text-center text-white">Profesor asignado</th>
                        <th class="text-center text-white">Nota obtenida</th>
                    </tr>

                   
                        <%for (Group g : groups) {%>
                        <tr>
                        <td class= "text-center text-white"> <%=g.getNrc()%> </td>
                        <td class= "text-center text-white"> <%=g.getTeach().getId() + "-" + g.getTeach().getName()%> </td>
                        <td class= "text-center text-white"> N/A </td>
                        </tr>
                        <% } %>

                </table>
            </div>
            <% }%>
            <div>&nbsp;</div>
            <div class="text-center">
                <a class ="btn btn-outline-light line-center" 
                   href="/ProyectoI/presentation/user/student/constancy">
                    Generar constancia
                </a> 
             </div>
        </div>

        <jsp:include page="/presentation/Footer.jsp"/>
    </body>
</html>

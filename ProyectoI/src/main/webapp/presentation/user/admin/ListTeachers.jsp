<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.awt.Image"%>
<%@page import="proyecto.model.Teacher"%>

<%HashMap<String, Teacher> teachers = (HashMap<String, Teacher>) session.getAttribute("teachers");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="/ProyectoI/images/landing-page.png" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de profesores</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Listado de Profesores</h1>
        <div class="container">
            <% if (teachers != null) { %>
            <div class="card-body bg-dark text-white">
                    <table class = "table table-condensed">
                    <tr>
                        <th class="text-center text-white">ID de profesor</th>
                        <th class="text-center text-white">Nombre del profesor</th>
                        <th class="text-center text-white">Dirección de correo electrónico</th>
                        <th class="text-center text-white">Numero telefónico del profesor</th>
                    </tr>
            <%for (Map.Entry<String, Teacher> entry : teachers.entrySet()) { %>
            <% Teacher tea = teachers.get(entry.getKey());%>
            <tr>
                <td class="text-center text-white"><%= tea.getId() %></td>
                <td class="text-center text-white"><%= tea.getName() %></td>
                <td class="text-center text-white"><%= tea.getEmail()%></td>
                <td class="text-center text-white"><%= tea.getTelNum() %></td>
            </tr>
            <% } %>
             </table>
             </div>
             <div>&nbsp;</div>
            <% }%>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>
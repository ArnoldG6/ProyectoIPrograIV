<%-- 
    Document   : R10
    Created on : Apr 16, 2021, 12:09:54 PM
    Author     : arnoldgq
--%>

<%@page import="proyecto.model.Group"%>
<%@page import="proyecto.model.Group"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="proyecto.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("user");%>
<%HashMap<String, Group> groups = (HashMap<String, Group>) session.getAttribute("groups"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show groups</title>
    </head>
    <body class="bg-dark text-center text-white">
        <jsp:include page="/presentation/Header.jsp"/>

        <div class="container">

            <% if (groups != null) { %>
            <h1 class="text-white text-center">Cursos registrados en el sistema</h1>
            <div class="row p-1 bg-dark column card-body w-15">
                <%for (Map.Entry<String, Group> entry : groups.entrySet()) { %>   
                <% Group sub = groups.get(entry.getKey());%>

                <div class="card p-3 bg-dark col w-15">
                    <table class = "table table-condensed border">Grupos del profesor
                        <tr>
                            <th class="text-left text-white">NRC</th>
                            <th class="text-left text-white">Nombre del curso</th>
                            <th class="text-left text-white">Numero de estudiantes</th>
                            <th class="text-left text-white">Estado</th>
                            <th class="text-left text-white">Ingresar</th>
                        </tr>
                        <tr>
                            <%for (Group g : groups.values()) {%>
                            <td class= "text-left text-white"> <%=g.getNrc()%> </td>
                            <td class= "text-left text-white"> <%=g.getTeacher().getNameSubj()%> </td>
                            <td class= "text-left text-white"> <%=Integer.toString(g.getNumStu())%> </td>
                            <td class= "text-left text-white"> <%=g.isStatus()%> </td>
                        <a class ="btn btn-outline-light" 
                           href="/ProyectoI/presentation/user/teacher/grades">
                            Ingresar
                        </a> 
                        </tr>
                        <% } %>
                    </table>
                </div>

                <% } %>

            </div>

            <% } else { %>
            <div class="text-white text-center"></div>
            <% }%>

        </div>

        <jsp:include page="/presentation/Footer.jsp"/>
    </body>
</html>

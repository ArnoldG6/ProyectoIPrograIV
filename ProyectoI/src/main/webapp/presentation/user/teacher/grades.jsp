<%-- 
    Document   : R11
    Created on : Apr 16, 2021, 12:10:11 PM
    Author     : arnoldgq
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="proyecto.model.Student"%>
<%@page import="java.util.Map"%>
<%@page import="proyecto.model.Group"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("user");%>
<%HashMap<String, Student> students = (HashMap<String, Student>) session.getAttribute("students"); %>
<% ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notas</title>
    </head>
    <body class="bg-dark text-center text-white">
        <jsp:include page="/presentation/Header.jsp"/>

        <div class="container">

            <% if (students != null) { %>
            <h1 class="text-white text-center">Cursos registrados en el sistema</h1>
            <div class="row p-1 bg-dark column card-body w-15">
                <%for (Map.Entry<String, Student> entry : students.entrySet()) { %>   
                <% Student sub = students.get(entry.getKey());%>

                <div class="card p-3 bg-dark col w-15">
                    <table class = "table table-condensed border">Estudiantes
                        <tr>
                            <th class="text-left text-white">Nombre</th>
                            <th class="text-left text-white">Identificacion</th>
                            <th class="text-left text-white">Correo</th>
                            <th class="text-left text-white">Telefono</th>
                        </tr>
                        <tr>
                            <%for (Student g : students.values()) {%>
                            <td class= "text-left text-white"> <%=g.getName()%> </td>
                            <td class= "text-left text-white"> <%=g.getId()%> </td>
                            <td class= "text-left text-white"> <%=g.getEmail()%> </td>
                            <td class= "text-left text-white"> <%=g.getTelNum()%> </td> 
                        </tr>
                        <% } %>
                    </table>
                </div>

                <% } %>

            </div>

            <div class="container">
                <div class = "form-group">
                    <label for = "regId">Número de identificación del estudiante</label>
                    <input type="text" class ="form-control  text-center" name = "id" placeholder = "Número de identificación" id = "id">
                    <label for = "regNom"> Nota del estudiante</label>
                    <input type="text" class ="form-control text-center" name = "grade" placeholder = "Nota del estudiante" id = "grade" >
                    <div class="text-center">
                        <div>&nbsp;</div>
                        <div>&nbsp;</div>
                        <button class ="btn btn-outline-light form-control w-50" href="/ProyectoI/presentation/user/teacher/grade">>Ingresar nota</button>
                    </div>
                    <% if (errors != null) {%>
                    <div>&nbsp;</div>
                    <div class="text-center"><label>Error: <%=errors.toString()%></label></div>
                    <% request.removeAttribute("errors"); %>
                    <div>&nbsp;</div>
                    <%}%>
                </div>
            </div>

            <% } else { %>
            <div class="text-white text-center"></div>
            <% }%>

        </div>

        <jsp:include page="/presentation/Footer.jsp"/>
    </body>
</html>

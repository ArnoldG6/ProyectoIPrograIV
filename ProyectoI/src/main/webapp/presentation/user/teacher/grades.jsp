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
        <link rel="icon" href="/ProyectoI/images/landing-page.png" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notas</title>
    </head>
    <body class="bg-dark text-center text-white">
        <jsp:include page="/presentation/Header.jsp"/>

        <div class="container">

            <% if (students != null) { %>
            <h1 class="text-white text-center">Lista de estudiantes</h1>
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
                <form class = "container center_div w-75 p-3" name = "gradesForm" href="/ProyectoI/presentation/user/teacher/grade" method="GET">
                    <div class = "form-group">
                        <label for = "regId">Número de identificación del estudiante</label>
                        <div>&nbsp;</div>
                        <input type="text" class ="form-control  text-center" name = "id" placeholder = "Número de identificación" id = "id">
                        <div>&nbsp;</div>
                        <label for = "regNom"> Nota del estudiante</label>
                        <div class = "text-center">
                            &nbsp;
                            <input type="text" class ="form-control text-center" name = "grade" placeholder = "Nota del estudiante" id = "grade" >
                        </div>
                        <div>&nbsp;</div>
                        <div class="text-center">
                            <div>&nbsp;</div>
                            <button class ="btn btn-outline-light form-control w-50">Ingresar nota</button>
                        </div>
                        <% if (errors != null) {%>
                            <div>&nbsp;</div>
                            <div class="text-center"><label>Error: <%=errors.toString()%></label></div>
                            <% request.removeAttribute("errors"); %>
                            <div>&nbsp;</div>
                        <%}%>
                    </div>
                </form>
            </div>

            <% } else { %>
            <div class="text-white text-center"></div>
            <% }%>

        </div>

        <jsp:include page="/presentation/Footer.jsp"/>
    </body>
</html>

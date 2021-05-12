<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.awt.Image"%>
<%@page import="proyecto.model.Teacher"%>
<% ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");%>
<%HashMap<String, Teacher> teachers = (HashMap<String, Teacher>) session.getAttribute("teachers");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear grupos</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Creación de grupos</h1>
        <div class="container">
            <form class = "container center_div w-75 p-3" name = "regForm" action="/ProyectoI/presentation/register/RegisterGroup" method="POST">
                <div class = "form-group">
                    <div class = "text-center">
                        <label for = "groupId">ID del grupo</label>
                        <div>&nbsp;</div>
                        <input type="text" class ="form-control text-center" name = "groupId"  id = "groupId" readonly
                               value="ID del grupo">
                        <div>&nbsp;</div>
                        <label for = "subId">ID de la materia: &#160;</label>
                        <div>&nbsp;</div>
                        <select class="combo" name = "subId" id = "subId">
                            <option value="2">sssssssssssssssssssssssssssssssss</option>
                        </select>
                        <div>&nbsp;</div>
                        <label for = "subCapacity">Capacidad de estudiantes: &#160;</label>
                        <div>&nbsp;</div>
                        <select class="combo" name = "subCapacity" id = "subCapacity">
                            <option value=15 >15</option>
                            <option value=20 >20</option>
                            <option value=30 >30</option>
                            <option value=40 >40</option>
                        </select>
                        <div>&nbsp;</div>
                        <label for = "teaGroupId">Profesor a seleccionar: &#160;</label>
                        <div>&nbsp;</div>
                        <select class="combo" name = "teaGroupId" id = "teaGroupId">
                            <option value=15 >Profesor girafales</option>
                        </select>
                        <div>&nbsp;</div>
                    </div>


                    <div class = "text-center">
                        <button class ="btn btn-outline-light form-control w-50">Completar registro</button>
                    </div>
                    <% if (errors != null) {%>
                    <label>Error: <%=errors.toString()%></label></div>
                    <% request.removeAttribute("errors"); %>
                <div>&nbsp;</div>
                <%}%>
            </form>          
            <% if (teachers != null) { %>
            <div>&nbsp;</div>
            <% }%>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

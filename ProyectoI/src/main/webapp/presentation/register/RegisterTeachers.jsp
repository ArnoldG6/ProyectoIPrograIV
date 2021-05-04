<%@page import="proyecto.presentacion.login.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Profesores</title>
    </head>
    <body  class="d-flex flex-column min-vh-100 bg-dark text-white">
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Registrar cuenta académica (Profesores)</h1>

        <form class = "container center_div w-75 p-3" name = "regForm" action="/ProyectoI/presentation/register/RegisterTeachers" method="POST">
            <div class = "form-group">
                <label for = "regTeaId">Número de identificación</label>
                <input type="text" class ="form-control  text-center" name = "regTeaId" placeholder = "Número de identificación" id = "regTeaId">
                <label for = "regTeaNom"> Nombre completo</label>
                <input type="text" class ="form-control text-center" name = "regTeaNom" placeholder = "Nombre completo" id = "regTeaNom" >
                <label for = "regTeaTel">Número telefónico</label>
                <input type="text" class ="form-control  text-center" name = "regTeaTel" placeholder = "Número telefónico" id = "regTeaTel">
                <label for = "regTeaEmail"> Dirección de correo electrónico</label>
                <input type="text" class ="form-control text-center" name = "regTeaEmail" placeholder = "Dirección de correo electrónico" id = "regTeaEmail" >
                <div class="text-center">
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <button class ="btn btn-outline-light form-control w-50">Completar registro</button>
                </div>
                <% if (errors != null) {%>
                <div>&nbsp;</div>
                <div class="text-center"><label>Error: <%=errors.toString()%></label></div>
                <% request.removeAttribute("errors"); %>
                <div>&nbsp;</div>
                <%}%>
            </div>
        </form> 
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>
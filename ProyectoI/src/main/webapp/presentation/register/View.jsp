<%@page import="proyecto.presentacion.login.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body  class="d-flex flex-column min-vh-100 bg-dark text-white">
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Registrar cuenta estudiantil</h1>

        <form class = "container center_div w-75 p-3" name = "regForm" action="/ProyectoI/presentation/login/register" method="POST">
            <div class = "form-group">
                <label for = "regId">Número de identificación</label>
                <input type="text" class ="form-control  text-center" name = "regId" placeholder = "Número de identificación" id = "regId">
                <label for = "regNom"> Nombre completo</label>
                <input type="text" class ="form-control text-center" name = "regNom" placeholder = "Nombre completo" id = "regNom" >
                <label for = "regTel">Número telefónico</label>
                <input type="text" class ="form-control  text-center" name = "regTel" placeholder = "Número telefónico" id = "regTel">
                <label for = "regEmail"> Dirección de correo electrónico</label>
                <input type="text" class ="form-control text-center" name = "regEmail" placeholder = "Dirección de correo electrónico" id = "regEmail" >
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


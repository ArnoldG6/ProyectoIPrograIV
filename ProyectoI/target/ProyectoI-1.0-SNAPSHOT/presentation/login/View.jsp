<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="proyecto.presentacion.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar</title>
    </head>
    <body  class="d-flex flex-column min-vh-100 bg-dark text-white">
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Digite sus credenciales: </h1>
        <form name = "form" class = "container center_div w-75 p-3" action="/ProyectoI/presentation/login/login" method="post">
            <div class = "form-group">
                <label for = "id">&nbsp;Identificación:</label>
                <input type="text" class ="form-control  text-center" name = "id" placeholder = "Número de identificación" id = "id">
                <label for = "pass">&nbsp; Contraseña:</label>
                <input type="password" class ="form-control text-center" name = "pass" placeholder = "Contraseña" id = "pass" >
                <div class="text-center">
                    <div>&nbsp;</div>
                    <button class ="btn btn-outline-light form-control w-50">Ingresar los datos</button>
                </div>

            </div>
        </form>
        <div class = "col-md-12 text-center">
            <a class="link-info" href="/ProyectoI/presentation/register/View.jsp">
                <label for "regBtn" >¿No tiene una cuenta? ¡Registrese aquí!</label>
            </a>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

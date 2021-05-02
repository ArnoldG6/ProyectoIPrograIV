<%@page import="proyecto.presentacion.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String genPass = (String) request.getAttribute("genPass");
   %>
<!DOCTYPE html>
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
                                                                                      
        <form class = "container center_div w-75 p-3"name = "regForm" action="/ProyectoI/presentation/login/register" method="POST">
            <div class = "form-group">
                <label for = "regId">Número de identificación</label>
                <input type="text" class ="form-control  text-center" name = "regId" placeholder = "Número de identificación" id = "regId">
                <label for = "regNom"> Nombre completo</label>
                <input type="text" class ="form-control text-center" name = "regNom" placeholder = "Nombre completo" id = "regNom" >
                <label for = "regTel">Número telefónico</label>
                <input type="text" class ="form-control  text-center" name = "regTel" placeholder = "Número telefónico" id = "regTel">
                <label for = "regEmail"> Dirección de correo electrónico</label>
                <input type="text" class ="form-control text-center" name = "reg_pass" placeholder = "Dirección de correo electrónico" id = "regEmail" >
                <div class="text-center">
                    <div>&nbsp;</div>
                    <% if (genPass != null) { %>
                        <label>Contraseña generada: <%=user.getId()%></label>
                    <%}%>

                    <div>&nbsp;</div>
                    <button class ="btn btn-outline-light form-control w-50">Completar registro</button>
                </div>

            </div>
        </form>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>


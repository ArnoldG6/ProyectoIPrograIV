<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String genPass = (String) request.getAttribute("genPass");
   %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Registro</title> 
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Contraseña autogenerada</h1>
        <div class="text-center">
            <% if (genPass != null) {%>
            <li class="list-inline-item">
                <div>Contraseña generada: &nbsp; <%=genPass%></div>
            </li>
            <% } %>
        </div>  
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

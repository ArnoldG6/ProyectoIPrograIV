<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de matricula</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">¡Bienvenido!</h1>
        <jsp:include page="/presentation/subjects/View.jsp"/>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
            
</html>

<%@page import="java.util.Map"%>
<%@page import="proyecto.model.Subject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HashMap<String, Subject> subjects = (HashMap<String, Subject>) session.getAttribute("subjects"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial de cursos</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Historial de cursos</h1> 
        

        
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

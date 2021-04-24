<%-- 
    Document   : index
    Created on : Apr 16, 2021, 11:58:19 AM
    Author     : arnoldgq
--%>

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
        <div class="btn-group-vertical container center_div w-75 p-3">
            <a class ="btn btn-outline-light" href="/ProyectoI/presentation/subjects/subjects.jsp">Listado de cursos en oferta</a>
            <div>&nbsp;</div>
            <a class ="btn btn-outline-light" href="/ProyectoI/presentation/user/student/enroll.jsp">Matricularse en un curso</a>
            <div>&nbsp;</div>
            <a class ="btn btn-outline-light" href="/ProyectoI/presentation/user/student/record.jsp">Historial de cursos</a>
            <div>&nbsp;</div>
            <a class ="btn btn-outline-light" href="/ProyectoI/presentation/user/student/constancy.jsp">General constancia del historial de cursos</a>
            <div>&nbsp;</div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
            
</html>

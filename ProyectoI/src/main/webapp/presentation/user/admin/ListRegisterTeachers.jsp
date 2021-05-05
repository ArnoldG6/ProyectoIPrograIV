<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de profesores</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Registro y listado de Profesores</h1>
        <div>
            <ul class ="list-inline">
                <li class="list-inline-item">
                    <a class ="btn btn-outline-light" href="/ProyectoI/presentation/register/RegisterTeachers.jsp">
                        Registro de Profesores
                    </a> 
                </li>
                <li class="list-inline-item">
                    <a class ="btn btn-outline-light" href="/ProyectoI/presentation/user/admin/ListTeachers">
                        Listado de Profesores
                    </a> 
                </li>
            </ul>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>
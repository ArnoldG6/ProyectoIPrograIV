<%-- 
    Document   : indexAmazon
    Created on : 02/05/2021, 05:50:24 PM
    Author     : victo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white">
        <%@ include file="/presentation/Header.jsp" %>

        <p align='center' class="w-75 p-3">

            <input class="width: 300px; height: 5000px;" type="search" name="busquedacursos" placeholder="Buscar cursos" >

            <input type="submit" value="Buscar">

        </p>
        <div class="container">
            <div class="card-group p-3 bg-dark row row-col-2" >
                <div class="card p-3 bg-dark col " >
                    <a href="#" id="imagen1" class="p-3 bg-dark " >
                        <img class="card-img-top" src="/ProyectoI/images/Buda.jpg" height="100" width = "100" alt="Card image cap" >
                    </a>
                    <div class="card-body">
                        <h5 class="card-title">Materia 1</h5>
                        <div>&nbsp;</div>
                        <a class ="btn btn-outline-light row justify-content-center" href="/ProyectoI/presentation/">Matricular ahora</a>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

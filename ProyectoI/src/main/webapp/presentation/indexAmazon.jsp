<%-- 
    Document   : indexAmazon
    Created on : 02/05/2021, 05:50:24 PM
    Author     : victo
--%>

<%@page import="proyecto.model.Subject"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white">
        <%@ include file="/presentation/Header.jsp" %>
        
        <p>

            <input type="search" name="busquedacursos" placeholder="Buscar cursos">

            <input type="submit" value="Buscar">

        </p>

        <div class="card-group p-3 bg-dark col-6 row mb-3" >
            <div class="card p-3 bg-dark col-6 themed-grid-col" >
                <!--<% List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
                    for (Subject c : subjects) {%>
                <!--<tr ><td><a target='_blank' href='/CursosImagenPdf/presentation/cursos/print?codigo=<%=c.getIdSub()%>'><%=c.getIdSub()%></a></td> <td><%=c.getNameSubj()%></td> <td><img src='/CursosImagenPdf/presentation/cursos/image?codigo=<%=c.getIdSub()%>'></td> </tr>-->
                <%}%>-->
                <a href="#" id="imagen1" class="p-3 bg-dark " >
                    <img class="card-img-top" src="/ProyectoI/images/Buda.jpg" height="100" width = "20" alt="Card image cap" >
                </a>
                <div class="card-body">
                    <h5 class="card-title">Materia 1</h5>
                    <div>&nbsp;</div>
                    <a class ="btn btn-outline-light row justify-content-center" href="/ProyectoI/presentation/">Matricular ahora</a>
                </div>
            </div>
        </div>

        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

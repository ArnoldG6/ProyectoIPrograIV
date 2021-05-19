<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.Group"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%HashMap<String, Group> studentGroups = (HashMap<String,Group>) request.getAttribute("studentGroups");%>
<%String exc = (String) session.getAttribute("exc");%>
<%String message = (String) session.getAttribute("message");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="/ProyectoI/images/landing-page.png" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Matricular</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Matricula de cursos</h1>  
                    <form class = "container center_div w-75 p-3" name = "enrollForm" 
                          action="/ProyectoI/presentation/student/enroll" method="POST">
                <div class = "form-group">
                    <div class = "text-center">
                        <label for = "subId">ID del curso &#160;</label>
                        <div>&nbsp;</div>
                        <select class="combo" name = "subId" id = "subId">
                            <option value="empty" selected="selected">Seleccione un grupo</option>

                        </select>
                        <div>&nbsp;</div>
                        <label for = "subCapacity">Capacidad de estudiantes &#160;</label>
                        <div>&nbsp;</div>
                        <select class="combo" name = "subCapacity" id = "subCapacity">
                            <option value="empty" selected="selected">Seleccione la capacidad para el grupo </option>
                            <option value=40>40</option>
                            <option value=30 >15</option>
                            <option value=20 >20</option>
                            <option value=15 >30</option>
                        </select>
                    </div>
                    <div class = "text-center">
                        <button class ="btn btn-outline-light form-control w-50">Completar matricula</button>
                    </div>
                    <% if (message != null) {%>
                    <div>&nbsp;</div>
                    <div class = "text-center" ><label class = "text-center" ><%=message%></label></div>
                    <% request.removeAttribute("message"); %>
                <div>&nbsp;</div>
                <%}%>
            </form> 
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

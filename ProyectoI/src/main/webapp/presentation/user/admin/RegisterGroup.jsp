<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.awt.Image"%>
<%@page import="proyecto.model.Teacher"%>
<%@page import="proyecto.model.Subject"%>
<%HashMap<String, Teacher> teachers = (HashMap<String, Teacher>) request.getAttribute("teachers");%>
<%HashMap<String, Subject> subjects = (HashMap<String, Subject>) request.getAttribute("subjects");%>
<%String exc = (String) session.getAttribute("exc");%>
<%String message = (String) session.getAttribute("message");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear grupos</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Creación de grupos</h1>
        <div class="container">
            <form class = "container center_div w-75 p-3" name = "regForm" action="/ProyectoI/presentation/RegisterGroup/RegisterGroup" method="POST">
                <div class = "form-group">
                    <div class = "text-center">
                        <label for = "subId">ID del curso &#160;</label>
                        <div>&nbsp;</div>
                        <select class="combo" name = "subId" id = "subId">
                            <option value="empty" selected="selected">Seleccione un curso</option>
                            <%if (subjects != null) {%>
                            <%for (Map.Entry<String, Subject> entry : subjects.entrySet()) { %>
                            <% Subject sub = subjects.get(entry.getKey());%>
                            <option value = <%= sub.getIdSub()%> >
                                <%= sub.getIdSub() + "-" + sub.getNameSubj()%>
                            </option>
                            <% } %>
                            <% } %>
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
                        <div>&nbsp;</div>
                        <label for = "teaGroupId">Profesor que impartirá el curso &#160;</label>
                        <div>&nbsp;</div>
                        <select class="combo" name = "teaGroupId" id = "teaGroupId">
                            <option value="empty" selected="selected">Seleccione un profesor </option>
                            <%if (teachers != null) {%>
                            <%for (Map.Entry<String, Teacher> entry : teachers.entrySet()) { %>
                            <% Teacher tea = teachers.get(entry.getKey());%>
                            <option value = <%= tea.getId() %> >
                                <%= tea.getId() + "-" + tea.getName()%>
                            </option>
                            <% } %>
                            <% } %>
                        </select>
                        <div>&nbsp;</div>
                    </div>
                    <div class = "text-center">
                        <button class ="btn btn-outline-light form-control w-50">Completar registro</button>
                    </div>
                    <% if (message != null) {%>
                    <div>&nbsp;</div>
                    <div class = "text-center" ><label class = "text-center" ><%=message%></label></div>
                    <% request.removeAttribute("message"); %>
                <div>&nbsp;</div>
                <%}%>
            </form>          
            <% if (exc != null) {%>
            <div>&nbsp;</div>
            <div class = "text-center" ><label><%=exc%></label></div>
            <% request.removeAttribute("exc"); %>
            <% }%>
    </div>
    <%@ include file="/presentation/Footer.jsp" %>
</body>
</html>

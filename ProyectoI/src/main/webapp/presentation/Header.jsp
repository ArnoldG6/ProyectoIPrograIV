<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<% User user = (User) session.getAttribute("user");%>
<%@ include file="/presentation/Head.jsp" %>
<header class="bg-dark text-center text-white">
    <div class="d-grid gap-3">
        <div class="p-1 bg-dark border">
            <h1>Sistema de matricula</h1>
        </div>
        <div class="p-3 bg-dark border w-90 mx-auto">
            <img class="w-75 mx-auto" src="/ProyectoI/images/whiteboard_img.jpg" height="120" width = "960">
        </div>
        <div></div>
    </div> 
    <div>
        <ul class ="list-inline "> 
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" href="/ProyectoI/">Inicio</a>
            </li>
            <% if (user != null) { %>
            <% if (!(user.getId().equals(""))) { %>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" href="/ProyectoI/presentation/login/logout">Cerrar sesión</a>
            </li>
            <% if (user.getType() == 3) { %>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" 
                   href="/ProyectoI/presentation/subjects/registerSubject.jsp">
                    Ingresar cursos
                </a> 
            </li>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" 
                   href="/ProyectoI/presentation/RegisterGroup/View">
                    Apertura de grupos
                </a> 
            </li>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" 
                   href="/ProyectoI/presentation/user/admin/ListRegisterTeachers.jsp">
                    Listado y registro de profesores
                </a> 
            </li>
            <% } %>
            <% if (user.getType() == 2) { %>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" 
                   href="/ProyectoI/presentation/user/student/record">
                    Historial de cursos
                </a> 
            </li>
            <% } %>
            <% if (user.getType() == 1) { %>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" 
                   href="/ProyectoI/presentation/user/teacher/groups">
                    Historial Grupos
                </a> 
            </li>
            <% } %>
            <% } else {%>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" href="/ProyectoI/presentation/login/View.jsp">Ingresar</a>
            </li>
            <% } %>
            <% } else { %>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" href="/ProyectoI/presentation/login/View.jsp">Ingresar</a>
            </li>
            <% } %>
        </ul>
        <ul class ="list-inline"> 
            <% if (user != null) { %>
            <% if (!(user.getId().equals(""))) {%>
            <li class="list-inline-item">
                <div>Usuario actual: &nbsp; <%=user.getId()%></div>
            </li>
            <% } %>
            <% }%>
        </ul>
    </div>
</header>          



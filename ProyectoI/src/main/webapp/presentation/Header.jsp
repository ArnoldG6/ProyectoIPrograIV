<%@page import="proyecto.model.User"%>
<% User user = (User) session.getAttribute("user");%>
 <%@ include file="/presentation/Head.jsp" %>
<header class="bg-dark text-center text-white">
    <div>
        <h1>Sistema de matricula</h1>
        <img src="/ProyectoI/images/whiteboard_img.jpg" height="160" width = "960">
    </div> 
    <div>
        <ul class ="list-inline"> 
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" href="/ProyectoI/presentation/login/View.jsp">Ingresar</a>
            </li>
            <li class="list-inline-item">
                <a class ="btn btn-outline-light" href="/ProyectoI/">Inicio</a>
            </li>
            
        </ul>
    </div>
</header>          



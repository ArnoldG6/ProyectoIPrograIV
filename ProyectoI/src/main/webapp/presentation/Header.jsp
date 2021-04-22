<%@page import="proyecto.model.User"%>
<% User user = (User) session.getAttribute("user");%>
 <%@ include file="/presentation/Head.jsp" %>
<header class="bg-dark text-center text-white">
    <div class="d-grid gap-3">
        <div class="p-1 bg-dark border">
            <h1>Sistema de matricula</h1>
         </div>
        <div class="p-3 bg-dark border">
            <img src="/ProyectoI/images/whiteboard_img.jpg" height="120" width = "960">
        </div>
        <div></div>
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



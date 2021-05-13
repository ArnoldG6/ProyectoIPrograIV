<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.User"%>
<%@page import="proyecto.presentacion.login.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.awt.Image"%>
<%@page import="proyecto.model.Subject"%>
<% User user = (User) session.getAttribute("user");%>
<%HashMap<String, Subject> searchedSbjts = (HashMap<String, Subject>) session.getAttribute("searchedSbjts"); %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="/ProyectoI/images/landing-page.png" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado de busqueda</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white">
        <jsp:include page="/presentation/Header.jsp"/>
        <div class="container">
            <% if (searchedSbjts != null) { %>
            <h1 class="text-white text-center">Cursos que coinciden con la búsqueda</h1>
            <div class="row p-1 bg-dark column card-body w-15">
            <%for (Map.Entry<String, Subject> entry : searchedSbjts.entrySet()) { %>   
            <% Subject sub = searchedSbjts.get(entry.getKey());%>
                <div class="card p-3 bg-dark col w-15">
                    <a href="#" id="imagen1" class="p-3 bg-dark text-center" >
                        <img src='/ProyectoI/presentation/subjects/image?subId=<%=sub.getIdSub()%>' height="150" width = "150">
                    </a>
                    <div><label class="card-title text-center"><%= sub.getNameSubj()%></label></div>
                    <div>&nbsp;</div>
                    <div><label class="text-center"><%= sub.getIdSub()%></label></div>
                    <div>&nbsp;</div>
                    <div><label class="text-center"><%= sub.getDesc()%></label></div>
                        <% if (user == null) {%>
                    <div><a class ="btn btn-outline-light container center_div w-75 p-1 " href="#">Matricular ahora</a></div>
                    <%} else {%>
                    <% if (user.getType() != 3) {%>
                    <div><a class ="btn btn-outline-light container center_div w-75 p-1" href="#">Matricular ahora</a></div>
                    <%}%>
                    <%}%>
                </div>
            <% } %>
            </div>
            <% }else { %>
            <div class="text-white text-center"></div>
            <% }%>
        </div>
        <jsp:include page="/presentation/Footer.jsp"/>
    </body>
</html>
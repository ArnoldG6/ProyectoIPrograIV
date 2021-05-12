<%-- 
    Document   : R10
    Created on : Apr 16, 2021, 12:09:54 PM
    Author     : arnoldgq
--%>

<%@page import="java.util.Map"%>
<%@page import="proyecto.model.Subject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="proyecto.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("user");%>
<%HashMap<String, Subject> subjects = (HashMap<String, Subject>) session.getAttribute("subjects"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/presentation/Header.jsp"/>
        
                <div class="container">
            
            <% if (subjects != null) { %>
            <h1 class="text-white text-center">Cursos registrados en el sistema</h1>
            <div class="row p-1 bg-dark column card-body w-15">
            <%for (Map.Entry<String, Subject> entry : subjects.entrySet()) { %>   
            <% Subject sub = subjects.get(entry.getKey());%>

            
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

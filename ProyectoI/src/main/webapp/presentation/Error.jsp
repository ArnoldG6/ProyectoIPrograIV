<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String exc = (String)session.getAttribute("exc");%>
<!DOCTYPE html>
<html>
    <head>
     <link rel="icon" href="/ProyectoI/images/landing-page.png" >
     <%@ include file="/presentation/Head.jsp" %>
     <title>Error</title> 
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Ha ocurrido un error</h1>
        <% if (exc != null) {%>
            <div class = "text-center"><%=exc%></div>
            <%session.removeAttribute("exc");%>
        <% }%>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>
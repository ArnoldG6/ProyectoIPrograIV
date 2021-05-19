<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyecto.model.Group"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%HashMap<String, Group> studentGroups = (HashMap<String, Group>) session.getAttribute("studentGroups");%>
<%String exc = (String) session.getAttribute("exc");%>
<%String message = (String) session.getAttribute("message");%>
<%String subID = (String) request.getParameter("subID");%>
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
        <div class="container">
            <div class = "form-group">
                <div class = "text-center">
                    <label for = "subId">ID del curso seleccionado&#160;</label>
                    <div>&nbsp;</div>
                    <input class = "text-center text-white bg-dark" type="text" id="subID" name="subID" 
                           value =
                           <% if (subID != null) {%>
                           <%= subID%>
                           <% } else {%>
                           "Seleccione un grupo"
                           <% }%>
                           disabled>
                </div>

                <h1 class = "text-center">Listado de grupos disponibles</h1>
                <div class="container">
                    <% if (studentGroups != null) { %>
                    <div class="card-body bg-dark text-white">
                        <table class = "table table-condensed border">
                            <tr>
                                <th class="text-center text-white">NRC del grupo</th>
                                <th class="text-center text-white">Datos del profesor</th>
                                <th class="text-center text-white">Capacidad de estudiantes</th>
                                <th class="text-center text-white"></th>
                            </tr>
                            <%for (Map.Entry<String, Group> entry : studentGroups.entrySet()) { %>
                            <% Group group = studentGroups.get(entry.getKey());%>
                            <tr>
                                <td class="text-center text-white"><%= group.getNrc()%></td>
                                <td class="text-center text-white"><%=group.getTeach().getId() + "-" + group.getTeach().getName()%></td>
                                <td class="text-center text-white"><%= group.getNumStu()%></td>
                                <td class="text-center text-white">
                                    <a class ="text-left text-white" href="/ProyectoI/presentation/student/matricular?GID=<%=group.getNrc()%>">
                                        <button class ="btn btn-outline-light form-control">
                                            Matricular
                                        </button>
                                    </a>
                                </td>
                            </tr>
                            <% } %>
                        </table>
                    </div>
                    <div>&nbsp;</div>
                    <% }%>
                </div>    

                <div>&nbsp;</div>

                <% if (message != null) {%>
                <div>&nbsp;</div>
                <div class = "text-center" ><label class = "text-center" ><%=message%></label></div>
                    <% request.removeAttribute("message"); %>
                <div>&nbsp;</div>
                <%}%>
        </div>
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

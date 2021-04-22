<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="proyecto.presentacion.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
                <% Model model= (Model) request.getAttribute("model"); %>
                <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
                <% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar</title>
    </head>
    <body  class="d-flex flex-column min-vh-100 bg-dark text-white">
            <%@ include file="/presentation/Header.jsp" %>
            <h1>Digite sus credenciales: </h1>
            <form name = "form" action="/ProyectoI/presentation/login/login" method="post">
                <div class = "form-group">
                    <label for = "id">&nbsp;Identificación:</label>
                    <input type="text" class ="form-control <%=erroneo("id",errores)%>" name = "id" placeholder = "Número de identificación" id = "id" value="<%=form.get("id")[0]%>" title="<%=title("id",errores)%>" >
                    <label for = "pass">&nbsp; Contraseña:</label>
                    <input type="password" class ="form-control <%=erroneo("pass",errores)%>" name = "pass" placeholder = "Contraseña" id = "pass" value="<%=form.get("pass")[0]%>" title="<%=title("pass",errores)%>" >
                    <button class ="btn btn-outline-light form-control">Ingresar los datos</button>
                </div>
            </form>   
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

<%!
    private String erroneo(String campo, Map<String,String> errores){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return "is-invalid";
      else
        return "";
    }

    private String title(String campo, Map<String,String> errores){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return errores.get(campo);
      else
        return "";
    }

    private Map<String,String[]> getForm(Model model){
       Map<String,String[]> values = new HashMap<String,String[]>();
       values.put("id", new String[]{model.getCurrent().getId()});
       values.put("pass", new String[]{model.getCurrent().getPass()});
       return values;
    }
    
%>
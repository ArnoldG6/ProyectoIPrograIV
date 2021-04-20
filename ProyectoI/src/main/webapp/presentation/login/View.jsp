<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar</title>
    </head>
    <body>
        <%@ include file="/presentation/Header.jsp" %>
        <h1>Digite sus credenciales: </h1>
        <form>
            <div class = "form-group">
                <label for = "id">Identificación:</label>
                <input type="text" class ="form-control" placeholder = "Número de dentificación" id = "id">
                <label for = "pass">Contraseña:</label>
                <input type="text" class ="form-control" placeholder = "Contraseña" id = "pass">
            </div>
        </form>    
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="/ProyectoI/images/landing-page.png" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar cursos</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-dark text-white"> 
        <%@ include file="/presentation/Header.jsp" %>
        <h1 class = "text-center">Ingresar cursos</h1>   
        
        <div> 
            <form name = "subForm" class = "container center_div w-75 p-3" 
                  method="POST" enctype="multipart/form-data" 
                  action="/ProyectoI/presentation/subjects/register">
                <label for = "subId">Código del curso</label>
                <input type="text" class ="form-control  text-center" name = "subId" id = "subId" placeholder="Digite el codigo del curso">
                <label for = "subName"> Nombre del curso</label>
                <input type="text" class ="form-control text-center" name = "subName"  id = "subName" placeholder="Digite el nombre del curso" >
                <label for = "subDesc">Descripción del curso</label>
                <input type="text" class ="form-control  text-center" name = "subDesc"  id = "subDesc" placeholder="Digite la descripcion del curso" >
                <label for = "subImg">Archivo de imagen</label>
                <input type="file" class ="form-control text-center" name = "subImg"  id = "subImg" >
                <div class="text-center">
                    <div>&nbsp;</div>
                    <div>&nbsp;</div>
                    <!--<button class ="btn btn-outline-light form-control w-50" >Completar registro</button>-->
                    <input type="submit" value="Completar registro">
                </div>
     
            </form>
        </div>
        
        <%@ include file="/presentation/Footer.jsp" %>
    </body>
</html>
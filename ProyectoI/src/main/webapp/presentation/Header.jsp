<%@page import="Model.User"%>
<% User user = (User) session.getAttribute("user");%>

<header>
    <div>

        <img src="/ProyectoI/images/books_img.jpg" height="175" width = "1280">
        <h1>Sistema de matricula</h1>
    </div> 
    <div>
        <ul> 
            <li>
                <a href="/ProyectoI/index.jsp">Inicio</a>
            </li>
            <li>
                <a href="/ProyectoI/presentation/R2.jsp">Login</a>
            </li>


        </ul>
    </div>
</header>          


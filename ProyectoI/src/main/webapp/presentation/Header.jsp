<%@page import="Model.User"%>
<% User user=  (User) session.getAttribute("user");  %>

<header>
    <div class="logo">
        <span>Sistema de matricula</span>
        <!--<img src="/Guia/images/logo.png">-->
    </div> 
    <div class="menu">
        <ul> 
              <li>
                <a href="/Guia/presentation/Index.jsp">Inicio</a>
              </li>
                        <% if (user!=null){ %>
                <li>
                  <a href="/Guia/presentation/cliente/cuentas/show">Cuentas</a>
                  <ul>  <!--submenu --> </ul>
                </li>                        
                <li >
                  <a  href="/Guia/presentation/cliente/datos/show">User:<%=user.getId()%></a>
                  <ul>  <!--submenu --> </ul>
                </li> 
                <li >
                  <a  href="/Guia/presentation/login/logout">Logout</a>
                  <ul>  <!--submenu --> </ul>
                </li>                
                        <% } %>
                        <% if (user==null){%>
                <li>
                  <a href="/Guia/presentation/login/show">Login</a>
                </li>
                
                        <% }%>             
            </ul>
    </div>
  </header>          


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Web App</title>
  </head>
  <body>
    <h1>¡Únete a Culinarius!</h1>
    
    <h3>Añadir Usuario</h3>

    <%-- Mostrar mensaje de error si existe --%>
    <c:if test="${not empty error}">
        <div style="color: red; font-weight: bold;">
            ¡Vaya! ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/user/save" method="POST">
        <label for="username">Nombre de usuario:</label>
        <%-- value="${param.username}" recupera el valor enviado previamente si hubo error --%>
        <input id="username" type="text" name="username" value="${param.username}" required /><br />
        
        <label for="email">Correo:</label>
        <input id="email" type="email" name="email" value="${param.email}" required /><br />
        
        <label for="password">Contraseña:</label>
        <input id="password" type="password" name="password" required /><br />
        
        <input type="submit" value="Guardar" />
    </form>

    <a href="${pageContext.request.contextPath}">Inicio</a>
  </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>
      <c:choose> 
        <c:when test="${tipo == 'crear'}"> Registrar usuario </c:when>
        <c:when test="${tipo == 'editar'}"> Editar usuario </c:when> 
        <c:when test="${tipo == 'eliminar'}"> Eliminar usuario </c:when> 
      </c:choose>
    </title>
  </head>
  <body>
    
      <h1>
      <c:choose>
          <c:when test="${tipo == 'crear'}">¡Únete a Culinarius!</c:when>
        <c:otherwise>Culinarius </c:otherwise> 
 
      </c:choose>
    </h1>
    

    <h3>
      <c:choose>
        <c:when test="${tipo == 'crear'}">Añadir Usuario</c:when>
        <c:when test="${tipo == 'editar'}">Editar Usuario</c:when>
      </c:choose>
    </h3>

    <%-- Mostrar mensaje de error si existe --%>
    <c:if test="${not empty error}">
        <div style="color: red; font-weight: bold;">
            ¡Vaya! ${error}
        </div>
    </c:if>

    <c:set var="accion" value="${tipo}" />

    <form action="${pageContext.request.contextPath}/user/${accion}" method="POST">
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

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Web App</title>
  </head>
  <body>
    <nav>| <a href="${pageContext.request.contextPath}/user/register">Crear Nuevo Usuario</a> |</nav>
    
    <h1>Lista de Usuarios</h1>

    <%-- Mostrar mensajes de éxito o error --%>
    <c:choose>
      <c:when test="${!empty requestScope.mensaje}">
        <div style="color: green; font-weight: bold;">
          ${requestScope.mensaje}
        </div>
            
      </c:when>
      <c:when test="${!empty requestScope.error}">
        <div style="color: red; font-weight: bold;">
          ${requestScope.error}
        </div>
      </c:when>
    </c:choose>
    

    <c:choose>
      <c:when test="${!empty requestScope.users}">
        <table>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Fecha creacion</th>
            <th>Acciones</th>
          </tr>
          <c:forEach var="user" items="${requestScope.users }">
            <tr>
              <td>${user.id}</td>
              <td>${user.username}</td>
              <td>${user.email}</td>
              <td>${user.createdAt}</td>
              <td>
                <a href="${pageContext.request.contextPath}/user/edit?${user.id}" class="btn btn-warning">Editar</a>
                <a href="${pageContext.request.contextPath}/user/remove?${user.id}" class="btn btn-danger btn-sm"
                onclick="return confirm('¿Eliminar usuario ${user.username}?')">Eliminar</a>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <p>Oops! No hay Usuarios todavía!</p>
      </c:otherwise>
    </c:choose>
  </body>
</html>

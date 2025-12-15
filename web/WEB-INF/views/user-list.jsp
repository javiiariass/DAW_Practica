<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Web App</title>
  </head>
  <body>
    <nav>| <a href="${pageContext.request.contextPath}/user/register">Crear Nuevo Usuario</a> |</nav>
    <nav>| <a href="${pageContext.request.contextPath}/user/edit"> Editar Usuario</a> |</nav>
    <nav>| <a href="${pageContext.request.contextPath}/user/remove"> Eliminar Usuario</a> |</nav>
    <h1>Web App</h1>
    <c:choose>
      <c:when test="${!empty requestScope.users}">
        <table>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Fecha creacion</th>
          </tr>
          <c:forEach var="user" items="${requestScope.users }">
            <tr>
              <td>${user.id}</td>
              <td>${user.username}</td>
              <td>${user.email}</td>
              <td>${user.createdAt}</td>
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

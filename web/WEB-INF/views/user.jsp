<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Web App</title>
  </head>
  <body>
    <nav>| <a href="/user/register">Crear Nuevo Usuario</a> |</nav>
    <h1>Web App</h1>
    <c:if test="${!empty requestScope.users}">
      <table>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Correo</th>
          <th>Teléfono</th>
        </tr>
        <c:forEach var="user" items="${requestScope.users }">
          <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <c:if test="${empty requestScope.users}">
      <p>Oops! No hay Usuarios todavía!</p>
    </c:if>
  </body>
</html>

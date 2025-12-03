<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Web App</title>
  </head>
  <body>
    <h1>¡Únete a Culinarius!</h1>
    <h3>Añadir Usuario</h3>

    <form action="${pageContext.request.contextPath}/user/save" method="POST">
      <label for="username">Nombre de usuario:</label>
      <input id="username" type="text" name="username" /><br />
      <label for="email">Correo:</label>
      <input id="email" type="text" name="email" /><br />
      <label for="password">Contraseña:</label>
      <input id="password" type="text" name="password" /><br />
      <input type="submit" value="Guardar" />
      
      
    </form>
    <a href="${pageContext.request.contextPath}">Inicio</a>
  </body>
</html>

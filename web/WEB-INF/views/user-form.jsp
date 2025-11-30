<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Web App</title>
  </head>
  <body>
    <h1>Web App</h1>
    <h3>Añadir Usuario</h3>

    <form action="/user/save" method="POST">
      <label for="name">Nombre:</label>
      <input id="name" type="text" name="name" /><br />
      <label for="email">Correo:</label>
      <input id="email" type="text" name="email" /><br />
      <label for="phone">Teléfono: </label>
      <input id="phone" type="text" name="phone" /><br />

      <input type="submit" value="Guardar" />
    </form>
    <a href="/users">Inicio</a>
  </body>
</html>

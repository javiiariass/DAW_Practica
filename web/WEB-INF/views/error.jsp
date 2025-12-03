<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Error</title>
  </head>
  <body>
    <h1>¡Vaya! Algo salió mal.</h1>

    <!-- Mostrar solo el mensaje seguro -->
    <p>${requestScope.msg}</p>

    <a href="${pageContext.request.contextPath}">Volver a Inicio</a>
  </body>
</html>

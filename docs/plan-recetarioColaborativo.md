# Plan: MVP Recetario Colaborativo

Este plan estructura el desarrollo de una aplicación web Java EE (MVC) para la gestión colaborativa de recetas. Se prioriza la persistencia con JPA, la seguridad básica (Login/Roles) y las funcionalidades dinámicas (AJAX) requeridas.

## Fases de Desarrollo

### Fase 1: Modelo de Datos y Persistencia (Cimientos)
Antes de hacer webs, necesitamos que la base de datos sea sólida.
1.  **Refinar `User`**:
    *   Descomentar/Añadir campo `Role` (puede ser un `Enum` o `String`: "ADMIN", "USER").
    *   Añadir relación `@OneToMany` hacia `Recipe` (sus recetas creadas).
2.  **Refinar `Recipe`**:
    *   Añadir relación `@ManyToOne` hacia `User` (el autor).
    *   Solucionar Ingredientes: Para el MVP, sugiero usar `@ElementCollection` si tu proveedor JPA lo soporta fácil, o simplificarlo a un `@Lob String` (texto largo) donde guardes los ingredientes en formato JSON o texto plano, para no complicarte con tablas extra ahora mismo.
3.  **Relación Favoritos**:
    *   Crear una relación `@ManyToMany` entre `User` y `Recipe` para guardar los "Favoritos".

### Fase 2: Capa de Acceso a Datos (DAO/Servicios)
Para no ensuciar los Servlets con código SQL/JPA.
1.  **Crear `RecipeService` y `UserService` (o DAOs)**:
    *   Clases que encapsulen el `EntityManager`.
    *   Métodos necesarios: `create`, `edit`, `remove`, `findById`, `findAll`, `findByUser`.

### Fase 3: Autenticación (Login y Registro)
Sin usuarios no hay "colaboración".
1.  **Vistas (JSP)**:
    *   `web/login.jsp`: Formulario de usuario/email y contraseña.
    *   `web/register.jsp`: Formulario de registro.
2.  **Controlador (`UserController` o `AuthServlet`)**:
    *   Implementar `doPost` para recibir los datos.
    *   **Registro**: Crear entidad `User` y guardarla.
    *   **Login**: Buscar usuario, verificar contraseña (aunque sea texto plano para el MVP inicial, luego hash), y guardar el objeto `User` en la `HttpSession`.

### Fase 4: Gestión de Recetas (CRUD)
El núcleo de la aplicación.
1.  **Vistas (JSP)**:
    *   `web/recipes/list.jsp`: Mostrar todas las recetas (página principal).
    *   `web/recipes/view.jsp`: Ver detalle de una receta.
    *   `web/recipes/form.jsp`: Formulario único para Crear y Editar.
2.  **Controlador (`RecipeController`)**:
    *   Rutas como `/recipes/create`, `/recipes/save`, `/recipes/delete`.
    *   Manejar la subida de imágenes (usando `Part` de Servlet 3.0+).
    *   Asignar el usuario de la sesión como autor al crear.

### Fase 5: Planificador y AJAX (El valor añadido)
Cumpliendo los requisitos específicos de la propuesta.
1.  **Planificador (Sesión)**:
    *   No necesita base de datos obligatoriamente (según tu propuesta).
    *   Crear un `PlannerServlet` que añada IDs de recetas a una lista en `session.setAttribute("weeklyPlan", lista)`.
    *   Vista `web/planner.jsp` para visualizar esa lista.
2.  **Favoritos (AJAX)**:
    *   En `view.jsp`, un botón "Corazón".
    *   JavaScript (`fetch`) que llame a `/api/favorite?id=123`.
    *   El Servlet añade la receta a la lista `@ManyToMany` del usuario y devuelve un JSON `{success: true}` sin recargar la página.

## Consideraciones Adicionales
1.  **Ingredientes**: ¿Prefieres una entidad `Ingredient` separada (más complejo) o guardarlos como texto/JSON en `Recipe` (más simple para MVP)?
2.  **Imágenes**: ¿Las fotos de las recetas se guardarán en la Base de Datos (`byte[]`/`@Lob`) o en una carpeta del servidor?

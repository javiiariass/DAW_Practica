## Propuesta Aplicación Web DAW
### 🧑‍🍳Recetario Colaborativo
Plataforma Web donde los usuarios pueden registrarse para compartir sus propias recetas y descubrir las de otros usuarios.

- **Gestión de Usuarios y Roles**:

    - **Visitantes**: Pueden ver y buscar recetas.
        
    - **Usuarios Registrados**: Pueden subir, editar y eliminar sus propias recetas, (escribir comentarios en otras -> complejidad de implementarlo ?) y guardar sus recetas favoritas. 
    - **Usuarios administradores**: Varios usuarios tendrán el rol de administrador para asegurarse que los usuarios cumplen con las normas comunitarias y hay ambiente sano en la web. Podrán gestionar al resto de usuarios:
	    - Suspensión temporal de usuarios.
	    - Eliminar Usuarios.
	    - Eliminar contenido de otros usuarios.
		    - De forma temporal: posibilidad de revisión por parte del creador.
		    - De forma permanente.
		    - ==Buscar más ideas==
		
- **Entidad Principal (CRUD - "Receta")**:

    - La entidad principal es la **Receta**.
        
    - Un usuario registrado puede **crear** una nueva receta, subiendo una foto del plato terminado, rellenando ingredientes e instrucciones. También puede **leer** (ver) las suyas y las de otros, **actualizar** sus propias recetas y **borrarlas**.
	
> [!note] 
> **Posible idea *API***
> Permitir al usuario indicar marcas de los ingredientes que utiliza (si el usuario no quiere, usar productos genéricos en su defecto) y con ellos hacer uso de la API de [OpenFoodFacts](https://es.openfoodfacts.org) para calcular las calorías y macronutrientes de la receta.

- **Funcionalidad del "Carrito"**:
    
    - Se implementaría como una sección de **"Mis Recetas Favoritas"** o un **"Planificador de Menú Semanal"**. Los usuarios podrían añadir recetas a esta lista personal, que se gestionaría con sesiones.
	- Implementar un **Planificador de Menú Semanal** donde el usuario (en la sesión activa) pueda ir añadiendo recetas (tanto ajenas, propias o guardadas en favoritos) a un Menú para la semana, dando la opción a *descargar* el menú en varios formatos antes de cerrar sesión. 
		- Estaría bien avisar al usuario que si abandona la página y no guarda el planificador, se borrará??
		- Posibilidad de descargar en varios formatos: pdf, xml/json?, csv(o cualquiera de excel)
        
- **Uso de AJAX/Fetch**:
    
    - Se usaría para que un usuario pueda añadir una receta a su lista de "Favoritos" o al "Planificador" con un solo clic y sin recargar la página. También podría usarse para un sistema de valoración por estrellas dinámico.
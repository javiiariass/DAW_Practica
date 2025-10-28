# Desarrollo práctica DAW
## Preguntas al profesor
- [ ] El controlador debería hacer uso de *DAOs*??? (Data Access Object) en [[#^1ecec6]] [backend](#v1-mvp-funcional-requisitos-asignatura)
- [ ] 
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
## Roadmap por fases
### Análisis inicial
- [ ] DRS
	- [ ] Diagrama de casos de uso
- [ ] Diagrama de clases
- [ ] Diagrama entidad relación
### V1: MVP Funcional (Requisitos Asignatura)
- [ ] **Backend:** ^1ecec6
    - [ ] Implementar patrón MVC (Servlets/JSP).
    - [ ] Configurar acceso a datos (JPA).
- [ ] **Gestión de Usuarios:**
    - [ ] CRUD (Registro, Login).
    - [ ] Seguridad: Encriptación de contraseñas en BBDD.
    - [ ] Seguridad: Autenticación y Autorización.
- [ ] **Gestión de Recetas (Entidad Principal):**
    - [ ] CRUD completo de `Receta`.
    - [ ] Subida de imagen en la creación.
    - [ ] Autorización (solo propietario modifica/borra).
    - [ ] Campos de macros (entrada manual).
- [ ] **Funcionalidad "Carrito" (Planificador):**
    - [ ] Implementar Planificador Semanal.
    - [ ] Uso de Sesiones HTTP para persistencia temporal.
- [ ] **Frontend (Interacción):**
    - [ ] HTML/CSS/JS (base funcional).
    - [ ] Validación de formularios (JavaScript).
    - [ ] Peticiones asíncronas (AJAX/Fetch) para "Favoritos" (**preguntar al profesor. No entiendo bien esta parte**).
- [ ] **Sistema de Favoritos:**
    - [ ] CRUD de Favoritos (persistente en BBDD, asociado a usuario).

### V1.5: Capa de Presentación 🎨
- [ ] **Estilos:**
    - [ ] Integración de CSS personalizado.
    - [ ] Implementación de Bootstrap.
- [ ] **UX/UI:**
    - [ ] Refinamiento de la interfaz.
    - [ ] Diseño *responsive*.


### V2: Funcionalidad Extendida 🌟
- [ ] **Sistema de Comentarios:**
    - [ ] CRUD de la entidad `Comentario`.
    - [ ] Relaciones (N-1) con `Usuario` y `Receta`.
- [ ] **Sistema Social (Follow):** 
	- [ ] Implementar sistema de 'Seguimiento' (N-M entre `Usuario`-`Usuario`). 
	- [ ] Lógica de seguimiento unilateral (sin aprobación).
- [ ] **Integración de APIs Externas:**
    - [ ] Conexión a API (OpenFoodFacts).
    - [ ] Conexión a API (The Swiss Food Composition Database).
    - [ ] Cálculo automático de macros/calorías.
    - [ ] Conexión a API de stock de fotos (ej. Pexels, Unsplash). 
		- [ ] Añadir buscador de imágenes en el formulario de recetas.
- [ ] **Gestión de Administradores:**
    - [ ] Panel de moderación (suspender usuarios, borrar contenido).
- [ ] **Control de Visibilidad de Recetas:**
    - [ ] Añadir atributo "publica" (booleano) a la entidad `Receta`.
    - [ ] Permitir al usuario marcar sus recetas como públicas o privadas.
    - [ ] Lógica de autorización: Recetas privadas solo visibles por el propietario.
- [ ] **Funcionalidad "Carrito" (Planificador):**
	- [ ] Ampliar funcionalidad permitiendo al usuario descargar la planificación antes de cerrar sesión. Admitir distintos formatos (pdf, csv, xml)

## Fases del proyecto detalladas
### V1: Producto Mínimo Viable (MVP)

_Cumplir con requisitos de la guía_

- **Backend:**
    
    - Implementación del patrón **MVC** con Servlets/JSP.
    - Configuración de la conexión a la base de datos (JPA).
        
- **Gestión de Usuarios:**
    
    - CRUD de `Usuario`: Registro, Login y (opcionalmente) Perfil Básico.
        
    - Seguridad: **Encriptación de contraseñas** en la BBDD (**mirar métodos de cifrado disponibles en java**).
        
    - Autenticación (Login) y Autorización (control de acceso a rutas).
        
- **Gestión de Recetas:**
    
    - CRUD completo de la entidad `Receta`.
        
    - Formulario de creación/edición que incluya la **subida de imagen**.
        
    - Autorización: Asegurar que solo el propietario puede editar o borrar su receta.
        
    - Campos de macros (`calorias`, `proteinas`, etc.) para **entrada manual** del usuario.
        
- **Funcionalidad "Carrito":**
    
    - Implementación del **Planificador Semanal** no persistente.
        
    - Debe usar **Sesiones HTTP** para almacenar temporalmente las recetas añadidas.
        
- **Frontend (Funcional):**
    
    - HTML semántico (sin estilo de momento).
        
    - **Validación de formularios** (Registro, Login, Nueva Receta) mediante JavaScript.
        
    - Uso de **AJAX/Fetch** para añadir recetas a "Favoritos" sin recarga.
        
- **Sistema de Favoritos:**
    
    - Funcionalidad para que un usuario registrado guarde/quite recetas de su lista personal (persistente en BBDD).
        

---

### V1.5: Mejorar estilos

_Aplicar estilos al frontend_

- Integración de **CSS** personalizado para dar estilo a la plataforma.
    
- Implementación de **Bootstrap**  para un diseño _responsive_ y amigable.
    
- Refinamiento de la interfaz de usuario (UI/UX).
    

---

### V2: Mejoras y Funcionalidad Extendida

_Añadir funcionalidades extra  a la web _

- **Sistema de Comentarios:**
    
    - Implementación del CRUD para la entidad `Comentario`.
        
    - Creación de la relación `Comentario` N-1 `Usuario` y `Comentario` N-1 `Receta`.
        
    - Visualización de comentarios en la página de detalle de la receta.
		
- **Sistema de Seguimiento (Follow):** 
	- Se implementará una relación N-M (Usuario-Usuario) que permita a un usuario "seguir" a otro de forma unilateral (sin solicitud de aprobación).
- **Integración de APIs Externas:**
    
    - Conexión con **OpenFoodFacts** (para productos procesados/con código de barras).
	    
	- Conexión con **The Swiss Food Composition Database** (para alimentos base/no procesados).
		
    - Cálculo automático de macros/calorías basado en la selección de las APIs.
        
    - Modificación del formulario de `Receta` para buscar ingredientes en la API.
        
    - Cálculo automático de macros/calorías basado en la selección de la API (sobrescribiendo la entrada manual).
		
	- **Integración de API de Imágenes (Stock):** - El sistema permitirá al `Usuario Registrado`, además de subir su propia foto, buscar e importar una imagen desde un servicio de fotos de stock (ej. Pexels) mediante su API. - La entidad `Receta` deberá poder almacenar tanto un path local (V1) como una URL externa (V2) en su atributo `rutaImagen`.
- **Gestión de Administradores (Avanzada):**
    
    - Implementación de los casos de uso para `Administrador` (suspender usuarios, eliminar contenido ajeno).
	
- **Control de Visibilidad de Recetas:**
    - El `Usuario Registrado` podrá definir sus recetas como "Públicas" (visibles por todos) o "Privadas" (visibles por seguidores).
    - El sistema implementará la lógica de negocio para restringir el acceso a las recetas privadas.


## Documento de Requisitos (DRS) - V1
### Proyecto: Recetario Colaborativo

#### 1. Descripción General

El proyecto consiste en una aplicación web desarrollada en Java (stack MVC) que permite a los usuarios compartir y descubrir recetas de cocina. La V1 se centrará en las funcionalidades básicas de gestión de usuarios, gestión de recetas (CRUD), un sistema de favoritos persistente y un planificador de menú semanal basado en sesión sin centrarnos en los estilos del frontend.

#### 2. Actores del Sistema

1.  **Visitante (No autenticado):** Usuario anónimo que puede navegar por el sitio, ver el listado de recetas públicas y buscar recetas.
2.  **Usuario Registrado (Autenticado):** Usuario que ha iniciado sesión. Tiene todos los permisos del Visitante y, además, puede crear, editar y eliminar sus propias recetas, y gestionar su lista de favoritos.
3.  **Administrador:** (Definido para V1, pero con funcionalidad limitada). Un tipo de `Usuario Registrado` con permisos para futuras tareas de moderación. En V1, su funcionalidad es idéntica a la del Usuario Registrado. De momento solo se crearán los administradores directamente en la base de datos. No implementaré constructor para administradores.

#### 3. Requisitos Funcionales (RF)

##### RF.1: Gestión de Usuarios (Autenticación)
- **RF.1.1:** El sistema debe permitir a un `Visitante` registrarse como nuevo `Usuario Registrado`. Los campos mínimos serán: nombre de usuario, email y contraseña.
- **RF.1.2:** El sistema debe encriptar la contraseña del usuario antes de almacenarla en la base de datos.
- **RF.1.3:** El sistema debe permitir a un usuario (Visitante o Registrado) iniciar sesión (autenticación).
- **RF.1.4:** El sistema debe permitir a un `Usuario Registrado` cerrar sesión.
- **RF.1.5:** El sistema debe restringir el acceso a funcionalidades de creación o edición solo a usuarios autenticados.

##### RF.2: Gestión de Recetas (CRUD Principal)
- **RF.2.1:** Un `Usuario Registrado` debe poder crear una nueva `Receta`.
- **RF.2.2:** El formulario de creación debe incluir, como mínimo: título, descripción, instrucciones (texto), tiempo, porciones y una **imagen** (subida de fichero).
- **RF.2.3:** El formulario de creación debe incluir campos opcionales (nulables) para `calorías`, `proteínas`, `grasas` y `carbohidratos` (entrada manual en V1).
- **RF.2.4:** Todos los usuarios (Visitantes y Registrados) deben poder ver el listado de recetas publicadas.
- **RF.2.5:** Todos los usuarios deben poder ver la página de detalle de una receta específica.
- **RF.2.6:** Un `Usuario Registrado` solo debe poder **actualizar** las recetas de las que es propietario.
- **RF.2.7:** Un `Usuario Registrado` solo debe poder **eliminar** las recetas de las que es propietario.

##### RF.3: Sistema de Favoritos (Persistente)
- **RF.3.1:** Un `Usuario Registrado` debe poder añadir cualquier receta a su lista personal de "Favoritos".
- **RF.3.2:** Un `Usuario Registrado` debe poder eliminar una receta de su lista de "Favoritos".
- **RF.3.3:** La acción de añadir/quitar de favoritos debe realizarse de forma asíncrona (usando **AJAX/Fetch**) sin recargar la página.
- **RF.3.4:** Un `Usuario Registrado` debe poder consultar una página donde se listen todas sus recetas favoritas.

##### RF.4: Planificador Semanal (Sesión - "Carrito")
- **RF.4.1:** Cualquier usuario (Visitante o Registrado) debe poder añadir una receta a un "Planificador Semanal".
- **RF.4.2:** Esta funcionalidad debe implementarse usando **Sesiones HTTP**.
- **RF.4.3:** El contenido del planificador es temporal y se pierde al expirar la sesión.
- **RF.4.4:** El usuario debe poder ver las recetas añadidas a su planificador actual.
- **RF.4.5:** (Opcional V1) El usuario podrá exportar este planificador (PDF, JSON).

##### RF.5: Interfaz y Validación
- **RF.5.1:** El sistema debe usar **JavaScript** para realizar validaciones en el lado del cliente en los formularios (ej. registro, login, nueva receta).
- **RF.5.2:** Todos los usuarios deben poder realizar búsquedas simples de recetas (ej. por título).

#### 4. Requisitos No Funcionales (RNF)

- **RNF.1 (Arquitectura):** La aplicación debe seguir el patrón **MVC**.
- **RNF.2 (Tecnología Backend):** Se utilizará Java (Servlets/JSP o framework) y **JPA** para el acceso a datos.
- **RNF.3 (Seguridad):** La aplicación debe servirse bajo **HTTPS** (protocolo seguro).
- **RNF.4 (Seguridad):** Las contraseñas deben almacenarse usando un algoritmo de *hashing* seguro (ej. BCrypt).
- **RNF.5 (Estilo):** La V1 se centrará en la funcionalidad. La V1.5 se dedicará a la implementación de CSS y Bootstrap.

#### 5. Mejoras Planificadas (Post-V1)

- **V2:** Implementación de un sistema de `Comentarios` (Entidad `Comentario` con relaciones N-1 a `Usuario` y `Receta`).
- **V2:** Integración con APIs (OpenFoodFacts y The Swiss Food Composition Database) para el cálculo automático de macros.
- **V2:** Integración con APIs de webs de stock de imágenes
- **V2:** Funcionalidad avanzada de `Administrador` (moderación).

### Casos de Uso

Este listado detalla las interacciones funcionales que el sistema debe soportar, agrupadas por el actor que las inicia.

#### 🧑‍💻 Actor: Visitante (No Autenticado)

- **CU-01: Registrarse**
    
    - **Descripción:** Permite a un `Visitante` crear una nueva cuenta de `Usuario Registrado` proporcionando un nombre de usuario, email y contraseña.
        
- **CU-02: Iniciar Sesión**
    
    - **Descripción:** Permite a un usuario autenticarse en el sistema usando su email (o nombre de usuario) y contraseña.
        
- **CU-03: Ver Listado de Recetas**
    
    - **Descripción:** Permite al `Visitante` ver una lista paginada de todas las recetas públicas disponibles en la plataforma.
        
- **CU-04: Ver Detalle de Receta**
    
    - **Descripción:** Permite al `Visitante` seleccionar una receta del listado y ver toda su información (ingredientes, instrucciones, imagen, macros, etc.).
        
- **CU-05: Buscar Recetas**
    
    - **Descripción:** Permite al `Visitante` buscar recetas basándose en un término (ej. por título o descripción).
        
- **CU-06: Gestionar Planificador Semanal**
    
    - **Descripción:** Permite al `Visitante` añadir o quitar recetas de un planificador de menú temporal. Este planificador **debe usar la Sesión HTTP** y su contenido se perderá al cerrar el navegador (cumpliendo el requisito del "carrito").
        


#### Usuario Registrado (Autenticado)

_(Hereda todos los casos de uso del Visitante y además puede realizar los siguientes)_

- **CU-07: Cerrar Sesión**
    
    - **Descripción:** Permite al `Usuario Registrado` invalidar su sesión y salir del sistema.
        
- **CU-08: Crear Receta**
    
    - **Descripción:** Permite al `Usuario Registrado` rellenar un formulario para publicar una nueva receta, incluyendo la subida de una imagen y la entrada manual (opcional) de macros.
        
- **CU-09: Actualizar Receta Propia**
    
    - **Descripción:** Permite al `Usuario Registrado` editar la información de una receta que le pertenece. El sistema debe validar que solo el propietario pueda ejecutar esta acción.
        
- **CU-10: Eliminar Receta Propia**
    
    - **Descripción:** Permite al `Usuario Registrado` eliminar permanentemente una receta que le pertenece. El sistema debe validar la propiedad.
        
- **CU-11: Gestionar Favoritos**
    
    - **Descripción:** Permite al `Usuario Registrado` marcar o desmarcar una receta como favorita. Esta acción debe ser persistente en la BBDD y realizarse de forma asíncrona (usando **AJAX/Fetch**).
        
- **CU-12: Ver Lista de Favoritos**
    
    - **Descripción:** Permite al `Usuario Registrado` acceder a una página personal donde se listan todas las recetas que ha marcado como favoritas.
        

---

#### 🛡️ Actor: Administrador

- **Descripción:** En la V1, el `Administrador` hereda todos los casos de uso del `Usuario Registrado` pero no tiene funcionalidades exclusivas. Su rol se define para futuras extensiones (V2).
    

---

#### ⚙️ Casos de Uso Internos (Soporte Técnico)

Estos no son iniciados directamente por un actor, sino que son _incluidos_ (`<<include>>`) o _extendidos_ (`<<extend>>`) por otros casos de uso:

- **CU-SEC-01: Validar Formulario (Extensión)**
    
    - **Descripción:** El sistema valida los datos de entrada en el cliente (usando **JavaScript**) para los formularios (CU-01, CU-02, CU-08).
        
- **CU-SEC-02: Encriptar Contraseña (Inclusión)**
    
    - **Descripción:** El sistema aplica un _hash_ seguro a la contraseña del usuario durante el `CU-01: Registrarse` antes de guardarla en la BBDD.
        
- **CU-SEC-03: Subir Imagen (Inclusión)**
    
    - **Descripción:** El sistema procesa y almacena el archivo de imagen proporcionado durante el `CU-08: Crear Receta`.
        

### Diagrama de casos de Uso
```mermaid
%% Diagrama de Casos de Uso - V1

%% Tema MVC con estilos coherentes

%% javi (versión 1.0)

  

graph LR

    %% ==== ACTORES ====

    Visitante(["🧑‍💻 Visitante"])

    Usuario(["👤 Usuario Registrado"])

    Admin(["🛡️ Administrador"])

  

    %% ==== HERENCIA DE ACTORES ====

    Usuario -.->|Herencia| Visitante

    Admin -.-> |Herencia|Usuario

  

    %% ==== CASOS DE USO VISITANTE ====

    CU01([CU-01: Registrarse])

    CU02([CU-02: Iniciar Sesión])

    CU03([CU-03: Ver Listado de Recetas])

    CU04([CU-04: Ver Detalle de Receta])

    CU05([CU-05: Buscar Recetas])

    CU06([CU-06: Gestionar Planificador Semanal])

  

    Visitante --> CU01

    Visitante --> CU02

    Visitante --> CU03

    Visitante --> CU04

    Visitante --> CU05

    Visitante --> CU06

  

    %% ==== CASOS DE USO USUARIO REGISTRADO ====

    CU07([CU-07: Cerrar Sesión])

    CU08([CU-08: Crear Receta])

    CU09([CU-09: Actualizar Receta Propia])

    CU10([CU-10: Eliminar Receta Propia])

    CU11([CU-11: Gestionar Favoritos])

    CU12([CU-12: Ver Lista de Favoritos])

  

    Usuario --> CU07

    Usuario --> CU08

    Usuario --> CU09

    Usuario --> CU10

    Usuario --> CU11

    Usuario --> CU12

  

    %% ==== CASOS DE USO INTERNOS ====

    Validar([<<extend>> CU-SEC-01: Validar Formulario])

    Encriptar([<<include>> CU-SEC-02: Encriptar Contraseña])

    Subir([<<include>> CU-SEC-03: Subir Imagen])

  

    CU01 -.-> Encriptar

    CU08 -.-> Subir

    CU01 -.-> Validar

    CU02 -.-> Validar

    CU08 -.-> Validar

  

    %% ==== ESTILOS ====

    classDef actor fill:#3aa653,stroke:#333,stroke-width:2px,color:#fff;

    classDef usecase fill:#5dade2,stroke:#1f618d,stroke-width:2px,color:#fff;

    classDef internal fill:#bbb,stroke:#555,stroke-width:2px,color:#000,font-style:italic;

  

    class Visitante,Usuario,Admin actor;

    class CU01,CU02,CU03,CU04,CU05,CU06,CU07,CU08,CU09,CU10,CU11,CU12 usecase;

    class Validar,Encriptar,Subir internal;
```

### Diseño del sistema
#### Diagrama de Clases
##### Idea básica
```mermaid
classDiagram

direction LR

    %% Definición de la Clase Usuario %%

    class Usuario {

        -id: int

        -nombreUsuario: String

        -email: String

        -passwordHash: String

        -rol: String

        +registrar(String user, String email, String pass)

        +login(String user, String pass) : boolean

        +crearReceta(Receta r)

        +anadirFavorito(Receta r)

        +eliminarFavorito(Receta r)

    }

  

    %% Definición de la Clase Receta %%

    class Receta {

        -id: int

        -titulo: String

        -descripcion: String

        -instrucciones: String

        -tiempoPreparacion: int

        -porciones: int

        -rutaImagen: String

        -fechaCreacion: Date

        -calorias: Integer

        -proteinas: Float

        -grasas: Float

        -carbohidratos: Float

        +guardar()

        +actualizar()

        +eliminar()

    }

  

    %% Relaciones entre Clases %%

    %% Relación 1-N (Propiedad/Autoría)

    %% Un Usuario es propietario/creador de 0 o más Recetas.

    %% Una Receta pertenece exactamente a 1 Usuario.

    Usuario "1" -- "0..*" Receta : "es propietario de"

  

    %% Relación N-N (Favoritos)

    %% Un Usuario puede tener 0 o más Recetas favoritas.

    %% Una Receta puede ser favorita de 0 o más Usuarios.

    Usuario "0..*" -- "0..*" Receta : "marca como favorita"
```
##### Integración de Controladores y DAOs

```mermaid
classDiagram

    %% ENTIDADES JPA (V1)

    direction RL

    class Usuario {

        +Long id

        +String username

        +String email

        +String passwordHash

        +Role role

        +Date createdAt

        +boolean enabled

    }

    <<Entity>> Usuario

  

    class Receta {

        +Long id

        +String titulo

        +String descripcion

        +String instrucciones

        +Integer tiempoMinutos

        +Integer porciones

        +Double calorias nullable

        +Double proteinas nullable

        +Double grasas nullable

        +Double carbohidratos nullable

        +String rutaImagen

        +boolean publica

        +Date createdAt

        +Date updatedAt

        +List~IngredientValue~ ingredientes

    }

    <<Entity>> Receta

  

    class Favorito {

        +Long id

        +Long usuarioId

        +Long recetaId

        +Date addedAt

    }

    <<Entity>> Favorito

  

    %% VALUE OBJECT / EMBEDDABLE (ElementCollection)

    class IngredientValue {

        +String nombre

        +String cantidadTexto

        +Integer orden

    }

    <<ValueObject>> IngredientValue

  

    %% NO PERSISTENTES / SESIÓN / DTO

    class PlanificadorSesion {

        +String sessionId

        +List~Long~ recetaIds

        +addReceta(recetaId: Long)

        +removeReceta(recetaId: Long)

        +export(format: String) : Blob

    }

  

    class SessionUser {

        +Long id           // null si visitante

        +String username   // null si visitante

        +boolean authenticated

        +Set~Role~ roles

        +isAuthenticated(): boolean

    }

  

    %% ENUM

    class Role {

        +USER

        +ADMIN

    }

    <<Enum>> Role

  

    %% CONTROLLERS (MVC) - manejan peticiones, delegan en DAOs

    class UsuarioController {

        +register(req,res)

        +login(req,res)

        +logout(req,res)

        +getPerfil(req,res)

    }

  

    class RecetaController {

        +createReceta(req,res)

        +editReceta(req,res)

        +deleteReceta(req,res)

        +viewReceta(req,res)

        +listRecetas(req,res)

        +uploadImage(req,res)

    }

  

    class FavoritoController {

        +toggleFavorito(req,res)

        +listFavoritos(req,res)

    }

  

    class PlanificadorController {

        +addToPlan(req,res)

        +removeFromPlan(req,res)

        +viewPlan(req,res)

        +exportPlan(req,res)

    }

  

    class AdminController {

        +suspenderUsuario(req,res)

        +eliminarUsuario(req,res)

        +eliminarContenido(req,res)

    }

  

    %% DAOs / Repositorios

    class UsuarioDAO {

        +findById(id)

        +findByUsername(username)

        +save(usuario)

        +delete(id)

    }

  

    class RecetaDAO {

        +findById(id)

        +findPaged(filter)

        +save(receta)

        +delete(id)

    }

  

    class IngredienteDAO {

        +findByRecetaId(recetaId)

        +save(ingrediente)

        +deleteByRecetaId(recetaId)

    }

  

    class FavoritoDAO {

        +findByUsuarioId(userId)

        +exists(userId, recetaId)

        +save(favorito)

        +delete(id)

    }

  

    %% RELACIONES ENTRE ENTIDADES

    Usuario "1" --> "*" Receta : propietario

    Receta "1" --> "0..*" IngredientValue : contiene (ElementCollection)

    Usuario "1" --> "*" Favorito : marca como favorito

    Favorito "*" --> "1" Receta : receta

  

    %% DEPENDENCIAS (Controllers -> DAOs / Sesión)

    UsuarioController ..> UsuarioDAO : usa

    RecetaController ..> RecetaDAO : usa

    RecetaController ..> IngredienteDAO : usa

    FavoritoController ..> FavoritoDAO : usa

    PlanificadorController ..> PlanificadorSesion : usa

    AdminController ..> UsuarioDAO : usa

    AdminController ..> RecetaDAO : usa

  

    %% NOTAS (sintaxis: note for <CLASS> "line1\nline2")

    note for Receta "Macros (calorías, proteínas, grasas, carbohidratos)

    Entrada MANUAL en V1 — campos NULLABLE."

    note for Favorito "Persistente para cumplir RF.3. Mantiene addedAt (timestamp).

    Índice único recomendado (usuario_id, receta_id)."

    note for PlanificadorSesion "Guardado en SESIÓN HTTP (HttpSession).

    No persistente en BBDD en V1 (RF.4)."

    note for SessionUser "DTO de sesión: representa visitante o usuario autenticado.

    SessionUser.anonymous() para visitantes."

    note for UsuarioController "EndPoints /register, /login, /logout, /perfil

    Al autenticar: migrar tempFavorites desde sesión."

    note for RecetaController "EndPoints /recetas (GET|POST|PUT|DELETE), /recetas/{id}

    Autorización: propietario o ADMIN para edit/delete."

    note for FavoritoController "EndPoints /favorito/toggle, /favoritos

    Si no autenticado -> tempFavorites en sesión."

    note for PlanificadorController "EndPoints para gestionar planificador; usa la sesión para almacenar recetaIds."
```




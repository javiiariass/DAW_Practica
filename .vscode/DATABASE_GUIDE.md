# 🗄️ Configuración de Base de Datos - Compartir entre NetBeans y VS Code

## ✅ **Respuesta Corta: SÍ, puedes usar la MISMA base de datos**

**La base de datos puede ser compartida** entre NetBeans y VS Code sin problemas. Ambos IDE pueden trabajar simultáneamente con la misma BD.

---

## 🔍 **Tu Configuración Actual**

### **Base de Datos**
- **Tipo**: Apache Derby (JavaDB)
- **URL**: `jdbc:derby://localhost:1527/app_db`
- **Usuario**: `app`
- **Password**: `app`
- **JNDI**: `jdbc/db`

### **⚠️ PROBLEMA CRÍTICO en persistence.xml**

Tu archivo `src/conf/persistence.xml` tiene esta configuración:

```xml
<property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
```

**Esto significa que:**
- ✅ Cada vez que despliegas, **DESTRUYE** todas las tablas
- ✅ Luego las **RECREA** desde cero
- ✅ Ejecuta el script `initTest.sql` para datos iniciales

---

## 🎯 **Solución: Tres Opciones**

### **Opción 1: Mantener "drop-and-create" (Para Desarrollo/Testing)** ⚠️

**Cuándo usar**: Durante desarrollo activo cuando quieres empezar limpio cada vez.

**Ventajas:**
- ✅ Base de datos siempre limpia y consistente
- ✅ No hay datos "basura" de pruebas anteriores
- ✅ Ideal para testing

**Desventajas:**
- ❌ **PIERDES TODOS LOS DATOS** cada vez que redespliegas
- ❌ No puedes probar con datos persistentes

**Configuración (actual)**:
```xml
<property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
```

---

### **Opción 2: Cambiar a "create" (Crear solo si no existe)** 🌟 RECOMENDADO

**Cuándo usar**: Cuando quieres **mantener los datos** entre despliegues.

**Ventajas:**
- ✅ Los datos persisten entre despliegues
- ✅ Puedes trabajar con datos reales
- ✅ NetBeans y VS Code comparten los mismos datos
- ✅ Más realista para desarrollo

**Desventajas:**
- ⚠️ Si cambias el modelo, puede haber conflictos

**Configuración (cambia en `src/conf/persistence.xml`)**:
```xml
<!-- OPCIÓN A: Crear solo si no existe -->
<property name="jakarta.persistence.schema-generation.database.action" value="create"/>

<!-- OPCIÓN B: No hacer nada (solo usar la BD existente) -->
<property name="jakarta.persistence.schema-generation.database.action" value="none"/>
```

---

### **Opción 3: Usar "drop-and-create" solo en ciertos perfiles**

**Cuándo usar**: Cuando quieres flexibilidad.

**Idea**: Tener dos configuraciones:
- Una para **desarrollo** con `create` (mantiene datos)
- Una para **testing** con `drop-and-create` (limpia cada vez)

---

## 🔧 **Cómo Configurar Derby para Compartir entre NetBeans y VS Code**

### **Paso 1: Asegúrate de que Derby Network Server está ejecutándose**

**El mismo servidor Derby que usa NetBeans debe estar activo.**

#### **Verificar si Derby está ejecutándose:**
```powershell
netstat -ano | Select-String ":1527"
```

Si ves algo como:
```
TCP    0.0.0.0:1527    0.0.0.0:0    LISTENING    12345
```
✅ Derby está ejecutándose.

---

### **Paso 2: Iniciar Derby si no está ejecutándose**

#### **Opción A: Desde NetBeans (Más fácil)**
1. Abre NetBeans
2. Ve a **Services** → **Databases** → **Java DB**
3. Click derecho → **Start Server**

#### **Opción B: Desde línea de comandos**

**Ubicación de Derby con GlassFish:**
```powershell
cd "C:\SwUni\DAW\GlassFish_Server_7.0.14\javadb\bin"
.\startNetworkServer.bat
```

**O con Java directamente:**
```powershell
$env:DERBY_HOME = "C:\SwUni\DAW\GlassFish_Server_7.0.14\javadb"
java -jar "$env:DERBY_HOME\lib\derbyrun.jar" server start
```

**Dejar ejecutándose en segundo plano** (no cerrar el terminal).

---

### **Paso 3: Verificar que los recursos JDBC están configurados en GlassFish**

```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" list-jdbc-resources
```

Deberías ver: `jdbc/db` ✅

**Si no está, agrégalo con:**
```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" add-resources "setup\glassfish-resources.xml"
```

---

### **Paso 4: Probar la conexión**

```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" ping-connection-pool connPoolDB
```

Si ves: `Command ping-connection-pool executed successfully` ✅ Todo funciona!

---

## 🔄 **Workflow Completo: NetBeans + VS Code**

### **Escenario 1: Trabajando en NetBeans**
1. NetBeans tiene Derby ejecutándose automáticamente
2. Tu aplicación usa `jdbc/db` → `localhost:1527/app_db`
3. Los datos se guardan en la BD

### **Escenario 2: Cambias a VS Code**
1. Derby sigue ejecutándose (mismo servidor)
2. Tu aplicación en VS Code usa el MISMO `jdbc/db` → `localhost:1527/app_db`
3. **VES LOS MISMOS DATOS** que en NetBeans ✅

### **Escenario 3: Ambos al mismo tiempo**
✅ **SÍ, puedes ejecutar ambos simultáneamente**
- Ambos usan la misma BD Derby
- Comparten los mismos datos
- No hay conflicto (Derby maneja múltiples conexiones)

---

## ⚠️ **IMPORTANTE: Comportamiento según `persistence.xml`**

### **Con `drop-and-create` (actual):**
```
1. Despliegas en NetBeans → BD se destruye y recrea
2. Agregas datos en NetBeans → Datos guardados
3. Despliegas en VS Code → BD se DESTRUYE de nuevo y recrea
4. Los datos de NetBeans se PIERDEN ❌
```

### **Con `create` o `none` (recomendado):**
```
1. Despliegas en NetBeans → BD se crea (si no existe)
2. Agregas datos en NetBeans → Datos guardados
3. Despliegas en VS Code → BD NO se destruye
4. Los datos de NetBeans siguen ahí ✅
5. Ambos ven los mismos datos ✅
```

---

## 🛠️ **Cambio Recomendado en persistence.xml**

### **Para mantener datos entre despliegues:**

**Archivo**: `src/conf/persistence.xml`

**Cambia esto:**
```xml
<property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
```

**Por esto:**
```xml
<!-- Opción 1: Crear solo si no existe (RECOMENDADO para desarrollo) -->
<property name="jakarta.persistence.schema-generation.database.action" value="create"/>

<!-- Opción 2: No hacer nada (usar BD existente) -->
<!-- <property name="jakarta.persistence.schema-generation.database.action" value="none"/> -->
```

**Después del cambio:**
1. Haz un rebuild: `ant clean dist`
2. Despliega: `GlassFish: Build and Deploy`
3. Los datos ahora persisten entre despliegues ✅

---

## 📊 **Comparación de Opciones**

| Acción | drop-and-create | create | none |
|--------|-----------------|--------|------|
| **Primera vez** | Crea tablas | Crea tablas | ERROR si no existen |
| **Siguiente vez** | Destruye y recrea | No hace nada | No hace nada |
| **Datos persisten** | ❌ NO | ✅ SÍ | ✅ SÍ |
| **Ejecuta initTest.sql** | ✅ Siempre | ✅ Solo primera vez | ❌ Nunca |
| **Cambios en modelo** | ✅ Aplica automáticamente | ⚠️ Conflicto | ⚠️ Conflicto |
| **Uso recomendado** | Testing/desarrollo inicial | Desarrollo normal | Producción |

---

## 🆘 **Troubleshooting**

### **Problema: "Connection refused: connect" en puerto 1527**

**Causa**: Derby no está ejecutándose.

**Solución**:
1. Inicia Derby desde NetBeans: Services → Java DB → Start Server
2. O desde línea de comandos (ver Paso 2 arriba)

### **Problema: "Table already exists"**

**Causa**: Usas `create` pero las tablas ya existen.

**Solución**:
```xml
<!-- Cambia a 'none' para usar la BD existente -->
<property name="jakarta.persistence.schema-generation.database.action" value="none"/>
```

### **Problema: Los datos desaparecen al redesplegar**

**Causa**: Tienes `drop-and-create` activo.

**Solución**: Cambia a `create` o `none` (ver arriba).

### **Problema: "jdbc/db not found"**

**Causa**: Los recursos JDBC no están configurados en GlassFish.

**Solución**:
```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" add-resources "setup\glassfish-resources.xml"
```

---

## 📝 **Resumen de Recomendaciones**

1. ✅ **Mantén Derby ejecutándose** (desde NetBeans o línea de comandos)
2. ✅ **Cambia `persistence.xml` a "create"** para mantener datos
3. ✅ **Usa el mismo GlassFish** para ambos IDE
4. ✅ **Comparte la misma BD** entre NetBeans y VS Code
5. ✅ **No necesitas duplicar nada**

---

## 🎯 **Próximos Pasos**

1. **Verificar que Derby está ejecutándose**
2. **Decidir si quieres mantener datos** (cambiar persistence.xml)
3. **Probar la conexión** con ping-connection-pool
4. **Desplegar y probar** en VS Code

---

**¿Quieres que te ayude a cambiar la configuración de `persistence.xml` ahora?**

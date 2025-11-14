# 📋 Tareas de Ant para VS Code

Este archivo documenta las tareas de Ant configuradas en `tasks.json` para proyectos Java EE.

## 🚀 Cómo usar las tareas

1. **Abrir el menú de tareas**: `Ctrl+Shift+P` → `Tasks: Run Task`
2. **Ejecutar la tarea de build por defecto**: `Ctrl+Shift+B`
3. **Desde la terminal**: También puedes usar los comandos `ant` directamente

---

## 📦 Tareas Disponibles

### 🧹 **Ant: Clean**
- **Descripción**: Limpia los archivos compilados y generados
- **Comando**: `ant clean`
- **Uso**: Ejecuta antes de hacer un rebuild completo
- **Elimina**: Carpetas `build/` y `dist/`

### ⚙️ **Ant: Compile**
- **Descripción**: Compila solo el código Java sin generar el WAR
- **Comando**: `ant compile`
- **Uso**: Para verificar que el código compila correctamente
- **Problem Matcher**: Detecta errores de compilación de Java

### 🔨 **Ant: Build (dist)** ⭐ _[Por defecto]_
- **Descripción**: Compila el proyecto y genera el archivo WAR
- **Comando**: `ant dist`
- **Uso**: Tarea principal para construir el proyecto
- **Atajo**: `Ctrl+Shift+B`
- **Genera**: `dist/DAW_Practica.war`

### 🔄 **Ant: Rebuild**
- **Descripción**: Limpia y construye el proyecto desde cero
- **Comando**: `ant clean dist`
- **Uso**: Cuando quieres asegurar un build limpio
- **Equivalente a**: Ejecutar Clean + Build

### ▶️ **Ant: Run**
- **Descripción**: Despliega la aplicación en GlassFish
- **Comando**: `ant run`
- **Uso**: Para ejecutar la aplicación en el servidor
- **Nota**: El servidor GlassFish debe estar ejecutándose
- **Acceso**: http://localhost:8080/DAW_Practica

### 🚀 **Ant: Deploy**
- **Descripción**: Construye y despliega la aplicación
- **Comando**: `ant dist run`
- **Uso**: Build + Deploy en un solo paso
- **Recomendado**: Para desarrollo rápido

### ⏹️ **Ant: Undeploy**
- **Descripción**: Desinstala la aplicación del servidor
- **Comando**: `ant undeploy`
- **Uso**: Para remover la aplicación de GlassFish

### 🏗️ **Ant: Clean Build**
- **Descripción**: Ejecuta clean-build si está definido en build.xml
- **Comando**: `ant clean-build`
- **Uso**: Target personalizado si existe

### 🧪 **Ant: Test**
- **Descripción**: Ejecuta las pruebas unitarias del proyecto
- **Comando**: `ant test`
- **Uso**: Para ejecutar JUnit tests
- **Grupo**: test

---

## 🔧 Configuración Adicional

### Variables de Entorno Necesarias
- ✅ `ANT_HOME`: No es necesario, Ant está en el PATH
- ✅ `JAVA_HOME`: Apunta a tu instalación de JDK 21
- ✅ PATH incluye: `C:\Program Files\NetbeansBinaries_27\extide\ant\bin`

### Servidor GlassFish
- **Ubicación**: `C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish`
- **Dominio**: `domain1`
- **Puerto Admin**: 4848
- **Puerto HTTP**: 8080

---

## 💡 Atajos de Teclado Útiles

| Atajo | Acción |
|-------|--------|
| `Ctrl+Shift+B` | Ejecutar tarea de build por defecto |
| `Ctrl+Shift+P` → `Tasks: Run Task` | Abrir menú de tareas |
| `Ctrl+Shift+P` → `Tasks: Terminate Task` | Detener una tarea en ejecución |
| `Ctrl+Shift+P` → `Tasks: Rerun Last Task` | Volver a ejecutar la última tarea |

---

## 📝 Targets de Ant Disponibles

Para ver todos los targets disponibles en tu `build.xml`:
```bash
ant -projecthelp
```

Targets comunes en proyectos NetBeans:
- `ant clean` - Limpiar proyecto
- `ant compile` - Compilar
- `ant dist` - Generar WAR
- `ant run` - Desplegar y ejecutar
- `ant debug` - Ejecutar en modo debug
- `ant undeploy` - Desinstalar
- `ant test` - Ejecutar tests

---

## 🔍 Troubleshooting

### Problema: "ant: command not found"
**Solución**: 
1. Verifica que `C:\Program Files\NetbeansBinaries_27\extide\ant\bin` está en las variables de entorno PATH
2. Reinicia VS Code o recarga la ventana (`Ctrl+Shift+P` → `Developer: Reload Window`)

### Problema: "Cannot find GlassFish server"
**Solución**: 
1. Verifica que GlassFish está instalado en `C:\SwUni\DAW\GlassFish_Server_7.0.14`
2. Revisa el archivo `nbproject/private/private.properties`

### Problema: Errores de compilación
**Solución**:
1. Ejecuta `Ant: Clean` primero
2. Verifica que JAVA_HOME apunta a JDK 21
3. Revisa que todas las dependencias están en `lib/`

---

## 🎯 Workflow Recomendado

### Desarrollo Normal:
1. **Editar código**
2. **`Ctrl+Shift+B`** (Build) - Compila y genera WAR
3. **`Ant: Run`** - Despliega en GlassFish
4. **Probar en navegador**

### Después de cambios mayores:
1. **`Ant: Rebuild`** - Build limpio
2. **`Ant: Deploy`** - Build + Deploy

### Antes de hacer commit:
1. **`Ant: Clean`** - Limpiar archivos generados
2. **`Ant: Test`** - Ejecutar pruebas
3. Hacer commit

---

## 📚 Recursos Adicionales

- [Documentación de Apache Ant](https://ant.apache.org/manual/)
- [GlassFish Documentation](https://glassfish.org/documentation)
- [VS Code Tasks Documentation](https://code.visualstudio.com/docs/editor/tasks)

---

**Creado**: 14 de noviembre de 2025  
**Proyecto**: DAW_Practica  
**Ant Version**: 1.10.14

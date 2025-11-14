# 🔄 Cómo Reutilizar las Tareas de Ant en Otros Proyectos

Este documento explica cómo copiar y configurar las tareas de Ant en otros proyectos NetBeans o Java EE.

---

## 📁 Archivos a Copiar

Para reutilizar estas tareas en otro proyecto, solo necesitas copiar estos archivos:

### 1️⃣ **Archivo Principal de Tareas (Plantilla)**
- **Archivo**: `.vscode/tasks-template.json`
- **Destino**: `.vscode/tasks.json` en tu nuevo proyecto
- **Nota**: Este es el archivo limpio y reutilizable. Si ya existe un `tasks.json`, puedes copiar solo el array de "tasks" dentro del archivo existente

### 2️⃣ **Documentación (Opcional)**
- **Archivo**: `.vscode/TASKS_README.md`
- **Destino**: Carpeta `.vscode/` de tu nuevo proyecto
- **Uso**: Referencia rápida de todas las tareas disponibles

### 📝 **Diferencia entre archivos:**
- **`tasks.json`**: Configuración activa del proyecto actual
- **`tasks-template.json`**: Plantilla limpia para copiar a otros proyectos

---

## 🚀 Pasos para Configurar en un Nuevo Proyecto

### **Opción A: Proyecto NetBeans Web Application**

1. **Abre tu proyecto en VS Code**
   ```bash
   cd ruta/a/tu/proyecto
   code .
   ```

2. **Crea la carpeta `.vscode` si no existe**
   ```bash
   mkdir .vscode
   ```

3. **Copia el archivo de plantilla**
   ```powershell
   # Desde PowerShell
   Copy-Item "C:\Users\Javi\Documents\GitHub\UNI\DAW_Practica\.vscode\tasks-template.json" -Destination ".\.vscode\tasks.json"
   ```

4. **¡Listo!** Ya puedes usar:
   - `Ctrl+Shift+B` para build
   - `Ctrl+Shift+P` → `Tasks: Run Task` para ver todas las tareas

### **Opción B: Proyecto Ant Genérico**

Si tu proyecto usa Ant pero no es de NetBeans, es posible que algunos targets no estén disponibles:

1. **Copia el archivo de plantilla**
   ```powershell
   Copy-Item "C:\Users\Javi\Documents\GitHub\UNI\DAW_Practica\.vscode\tasks-template.json" -Destination "ruta/a/tu/proyecto/.vscode/tasks.json"
   ```

2. **Verifica qué targets existen en tu build.xml**
   ```bash
   ant -projecthelp
   ```

3. **Edita `tasks.json`** y elimina las tareas que no apliquen a tu proyecto

---

## 🛠️ Personalización por Proyecto

### **Cambiar el target de build por defecto**

Si tu proyecto usa un target diferente a `dist`, edita la tarea `Ant: Build (dist)`:

```json
{
    "label": "Ant: Build",
    "command": "ant",
    "args": ["jar"],  // Cambia "dist" por tu target
    "group": {
        "kind": "build",
        "isDefault": true  // Esta es la tarea por defecto con Ctrl+Shift+B
    }
}
```

### **Agregar Targets Personalizados**

Copia y modifica una tarea existente:

```json
{
    "label": "Ant: Mi Target Custom",
    "type": "shell",
    "command": "ant",
    "args": ["mi-target"],
    "problemMatcher": [],
    "presentation": {
        "reveal": "always",
        "panel": "shared"
    },
    "group": "build"
}
```

### **Configurar para Servidor Diferente**

Si usas un servidor diferente a GlassFish (Tomcat, WildFly, etc.), actualiza las tareas de deploy:

```json
{
    "label": "Ant: Deploy to Tomcat",
    "command": "ant",
    "args": ["deploy-tomcat"]  // Tu target específico
}
```

---

## 📦 Uso con Git

### **Incluir en el Repositorio**

Para que tu equipo también tenga las tareas:

```bash
git add .vscode/tasks.json
git add .vscode/TASKS_README.md
git commit -m "Add Ant tasks for VS Code"
git push
```

### **Excluir del Repositorio**

Si prefieres que las tareas sean personales:

Agrega a `.gitignore`:
```
.vscode/tasks.json
```

---

## 🎯 Proyectos que Pueden Usar Estas Tareas

✅ **Compatible con:**
- Proyectos NetBeans Web Application (Java EE)
- Proyectos NetBeans Enterprise Application
- Cualquier proyecto con `build.xml` de Ant
- Proyectos Java con servidor de aplicaciones (GlassFish, Tomcat, WildFly)

⚠️ **Puede requerir modificación:**
- Proyectos Ant con targets personalizados
- Proyectos sin servidor de aplicaciones (solo JAR)
- Proyectos con estructuras no estándar

❌ **No compatible con:**
- Proyectos Maven (usa tasks de Maven)
- Proyectos Gradle (usa tasks de Gradle)
- Proyectos sin sistema de build

---

## 🔧 Troubleshooting

### Problema: Las tareas no aparecen en el menú

**Solución:**
1. Verifica que `.vscode/tasks.json` existe en la raíz del proyecto
2. Recarga VS Code: `Ctrl+Shift+P` → `Developer: Reload Window`
3. Verifica que el JSON es válido (sin errores de sintaxis)

### Problema: "ant: command not found"

**Solución:**
1. Asegúrate de que Ant está en el PATH:
   ```powershell
   ant -version
   ```
2. Si no funciona, reinicia VS Code después de agregar Ant al PATH

### Problema: Target no encontrado

**Solución:**
1. Lista los targets disponibles:
   ```bash
   ant -projecthelp
   ```
2. Actualiza `tasks.json` con los targets correctos de tu proyecto

---

## 📝 Plantilla Mínima

Si solo quieres las tareas básicas, usa esta versión mínima:

```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "Ant: Clean",
            "type": "shell",
            "command": "ant",
            "args": ["clean"],
            "group": "build"
        },
        {
            "label": "Ant: Build",
            "type": "shell",
            "command": "ant",
            "args": ["dist"],
            "group": {
                "kind": "build",
                "isDefault": true
            }
        },
        {
            "label": "Ant: Rebuild",
            "type": "shell",
            "command": "ant",
            "args": ["clean", "dist"],
            "group": "build"
        }
    ]
}
```

---

## 📚 Recursos Adicionales

- [VS Code Tasks Documentation](https://code.visualstudio.com/docs/editor/tasks)
- [Apache Ant Manual](https://ant.apache.org/manual/)
- [Compartir tasks.json entre proyectos](https://code.visualstudio.com/docs/editor/tasks#_custom-tasks)

---

**Última actualización**: 14 de noviembre de 2025  
**Compatibilidad**: VS Code 1.80+, Apache Ant 1.10+

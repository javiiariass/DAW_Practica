# 🔄 Workflow: NetBeans + VS Code + Copilot

## 🎯 **Configuración Recomendada**

Este proyecto está optimizado para trabajar con:
- **NetBeans**: Ejecutar y desplegar la aplicación
- **VS Code + Copilot**: Editar y generar código

---

## ✨ **Cómo Trabajar**

### **Paso 1: Iniciar desde NetBeans**

```
1. Abre el proyecto en NetBeans
2. Presiona F6 (Run) o clic en "Run"
3. NetBeans automáticamente:
   ✅ Inicia GlassFish Server (puerto 8080)
   ✅ Inicia Derby Database (puerto 1527)
   ✅ Despliega tu aplicación
   ✅ Abre http://localhost:8080/app en navegador
```

### **Paso 2: Editar Código en VS Code**

```
1. Abre VS Code en este proyecto
2. Pídele a Copilot lo que necesites:
   
   Ejemplos:
   • "Agrega un método para buscar usuarios por email"
   • "Crea una clase DAO para gestionar recetas"
   • "Refactoriza este método para hacerlo más eficiente"
   • "Explícame qué hace este código"
   
3. Copilot modificará los archivos
4. Guarda los cambios (Ctrl+S)
```

### **Paso 3: Ver los Cambios**

**Opción A - Deploy on Save (Automático):**
Si tienes "Deploy on Save" activado en NetBeans:
- Los cambios se aplican automáticamente
- Refresca el navegador (F5)

**Opción B - Redespliegue Manual:**
1. Vuelve a NetBeans
2. Presiona F6 (Run) nuevamente
3. NetBeans redespliegue con tus cambios
4. Refresca el navegador (F5)

---

## 📋 **Ejemplo Práctico**

### **Escenario: Agregar una nueva funcionalidad**

```
┌─────────────────────────────────────────────────────────┐
│ 1. TÚ EN NETBEANS                                       │
├─────────────────────────────────────────────────────────┤
│ • Presionas F6 (Run)                                    │
│ • Aplicación ejecutándose                               │
│ • http://localhost:8080/app abierto                     │
└─────────────────────────────────────────────────────────┘
          │
          ↓
┌─────────────────────────────────────────────────────────┐
│ 2. TÚ EN VS CODE CON COPILOT                            │
├─────────────────────────────────────────────────────────┤
│ Tú: "Copilot, agrega un método en UserController       │
│      para obtener usuarios por rol"                     │
│                                                          │
│ Copilot: Edita UserController.java                      │
│          Agrega el método getUsersByRole(String role)   │
│                                                          │
│ Tú: Ctrl+S (Guardar)                                    │
└─────────────────────────────────────────────────────────┘
          │
          ↓
┌─────────────────────────────────────────────────────────┐
│ 3. TÚ VUELVES A NETBEANS                                │
├─────────────────────────────────────────────────────────┤
│ Opción A: Deploy on Save activo                         │
│   → Cambios ya aplicados automáticamente ✅             │
│                                                          │
│ Opción B: Manual                                        │
│   → F6 (Run) para redesplegar                           │
│   → Cambios aplicados ✅                                │
└─────────────────────────────────────────────────────────┘
          │
          ↓
┌─────────────────────────────────────────────────────────┐
│ 4. NAVEGADOR                                            │
├─────────────────────────────────────────────────────────┤
│ • F5 (Refrescar)                                        │
│ • Nueva funcionalidad disponible ✅                     │
└─────────────────────────────────────────────────────────┘
```

---

## ⌨️ **Comandos Útiles en VS Code**

| Acción | Comando |
|--------|---------|
| **Build WAR** | `Ctrl+Shift+B` |
| **Ver tareas** | `Ctrl+Shift+P` → `Tasks: Run Task` |
| **Guardar** | `Ctrl+S` |
| **Buscar archivo** | `Ctrl+P` |
| **Buscar en archivos** | `Ctrl+Shift+F` |

---

## 🚀 **Tareas Disponibles (5)**

| Tarea | Descripción | Cuándo usar |
|-------|-------------|-------------|
| `Ant: Build (dist)` | Genera el WAR | Para verificar que compila |
| `Ant: Clean` | Limpia build/ y dist/ | Antes de rebuild limpio |
| `Ant: Compile` | Solo compila | Para ver errores rápido |
| `Ant: Rebuild` | Clean + Build | Build desde cero |
| `Ant: Test` | Ejecuta tests | Antes de commit |

**Nota**: No necesitas tareas de despliegue porque lo haces desde NetBeans.

---

## 🌐 **Base de Datos Compartida**

### **✅ Ambos usan la misma BD**

- **URL**: `jdbc:derby://localhost:1527/app_db`
- **Usuario**: `app`
- **Password**: `app`

### **Importante sobre `persistence.xml`:**

Tu configuración actual:
```xml
<property name="jakarta.persistence.schema-generation.database.action" 
          value="drop-and-create"/>
```

**⚠️ Esto BORRA y RECREA la BD cada vez que despliegas**

**Opciones:**
- `drop-and-create` → Borra todo cada vez (útil para desarrollo)
- `create` → Solo crea si no existe (mantiene datos)
- `none` → No modifica la BD (producción)

**Para mantener datos entre despliegues:**
1. Cambia a `create` en `src/conf/persistence.xml`
2. Rebuild y redespliegue

---

## 💡 **Mejores Prácticas**

### **✅ DO (Hacer):**
- ✅ Ejecutar desde NetBeans (maneja todo automáticamente)
- ✅ Editar código en VS Code con Copilot
- ✅ Guardar cambios frecuentemente
- ✅ Usar `Ctrl+Shift+B` para verificar que compila
- ✅ Hacer commits pequeños y frecuentes

### **❌ DON'T (No hacer):**
- ❌ Intentar iniciar GlassFish desde VS Code (NetBeans lo hace)
- ❌ Modificar código en ambos IDEs simultáneamente
- ❌ Olvidar guardar antes de redesplegar

---

## 🆘 **Troubleshooting**

### **Problema: Los cambios no se reflejan**

**Solución:**
1. Asegúrate de haber guardado (Ctrl+S)
2. En NetBeans: Stop y luego Run (F6)
3. Refresca el navegador (F5 o Ctrl+F5)

### **Problema: Error de compilación**

**Solución:**
1. Lee el error en la salida de NetBeans
2. Pídele a Copilot que lo explique y corrija
3. Guarda y redespliegue

### **Problema: Base de datos no funciona**

**Solución:**
1. Verifica que NetBeans inició Derby (Services → Databases)
2. Revisa `persistence.xml` para configuración correcta
3. Consulta `DATABASE_GUIDE.md` para más detalles

---

## 📚 **Archivos de Documentación**

- **`WORKFLOW.md`** (este archivo) - Workflow NetBeans + VS Code
- **`QUICK_START.md`** - Referencia rápida de tareas
- **`TASKS_README.md`** - Documentación completa de tareas Ant
- **`DEPLOYMENT_GUIDE.md`** - Guía de despliegue (si lo haces desde VS Code)
- **`DATABASE_GUIDE.md`** - Configuración de base de datos
- **`REUSE_GUIDE.md`** - Copiar configuración a otros proyectos

---

## 🎉 **Resumen**

```
┌──────────────┐         ┌──────────────┐         ┌──────────────┐
│              │         │              │         │              │
│   NetBeans   │────────▶│   VS Code    │────────▶│   NetBeans   │
│   (Ejecutar) │         │   (Editar)   │         │ (Redesplegar)│
│              │         │              │         │              │
└──────────────┘         └──────────────┘         └──────────────┘
       │                        │                        │
       ↓                        ↓                        ↓
  GlassFish             Copilot modifica           App actualizada
  + Derby               archivos .java            http://localhost:8080/app
```

**El workflow ideal**: NetBeans maneja la infraestructura, Copilot te ayuda con el código. ✨

---

**Última actualización**: 14 de noviembre de 2025

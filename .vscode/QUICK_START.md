# ⚡ Quick Start - Ant Tasks

## 🎹 Atajos de Teclado

| Atajo | Acción |
|-------|--------|
| `Ctrl+Shift+B` | **Build** (Tarea por defecto: `ant dist`) |
| `Ctrl+Shift+P` | **Abrir Command Palette** → `Tasks: Run Task` |

---

## 🚀 Tareas Más Usadas

### Para Desarrollo Diario:
```
1. Ant: Build (dist)              → Compila y genera WAR
2. GlassFish: Build and Deploy    → Build + Deploy (RECOMENDADO) ⭐
3. GlassFish: Deploy with asadmin → Solo deploy (después de build)
```

### Para Gestión de GlassFish:
```
4. GlassFish: Start Server        → Iniciar GlassFish
5. GlassFish: Stop Server         → Detener GlassFish
6. GlassFish: Restart Server      → Reiniciar GlassFish
7. GlassFish: List Applications   → Ver apps desplegadas
```

### Para Limpieza:
```
8. Ant: Clean                     → Elimina build/ y dist/
9. Ant: Rebuild                   → Clean + Build
10. GlassFish: Undeploy           → Desinstala la app
```

### Otras (Ant):
```
11. Ant: Compile                  → Solo compila (sin WAR)
12. Ant: Run                      → Deploy con ant (puede fallar)
13. Ant: Test                     → Ejecuta tests
```

---

## 🔄 Workflow Típico

### **Opción 1: Workflow Recomendado (asadmin)**
```
1. Editar código
2. Ctrl+Shift+P → GlassFish: Build and Deploy
3. Abrir http://localhost:8080/app
```

### **Opción 2: Build + Deploy Manual**
```
1. Editar código
2. Ctrl+Shift+B                    (Build)
3. GlassFish: Deploy with asadmin  (Deploy)
4. Abrir http://localhost:8080/app
```

### **Opción 3: Usando Ant (si funciona)**
```
1. Editar código
2. Ant: Deploy         (hace todo en un paso)
3. Abrir http://localhost:8080/app
```

---

## 🌐 URLs del Proyecto

- **Aplicación**: http://localhost:8080/app
- **GlassFish Admin**: http://localhost:4848
- **Base de Datos**: Derby en localhost:1527 (app_db)

---

## 🗄️ Base de Datos

**Configuración:**
- **Tipo**: Apache Derby (JavaDB)
- **URL**: jdbc:derby://localhost:1527/app_db
- **Usuario/Password**: app/app
- **JNDI**: jdbc/db

**⚠️ IMPORTANTE**: Tu `persistence.xml` tiene `drop-and-create`, lo que significa que **los datos se borran cada vez que despliegas**. 

**Para mantener datos**, cambia en `src/conf/persistence.xml`:
```xml
<!-- Cambia esto -->
<property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>

<!-- Por esto -->
<property name="jakarta.persistence.schema-generation.database.action" value="create"/>
```

**Tareas de Derby:**
- `Derby: Start Network Server` - Iniciar Derby
- `Derby: Check Status` - Verificar si está ejecutándose
- `Derby: Test Connection` - Probar conexión

📖 **Lee DATABASE_GUIDE.md** para más detalles

---

## 💡 Comandos Directos en Terminal

También puedes usar Ant directamente:

```bash
ant clean          # Limpiar
ant compile        # Compilar
ant dist           # Build
ant run            # Deploy
ant clean dist     # Rebuild
ant undeploy       # Desinstalar
ant -projecthelp   # Ver todos los targets
```

---

## 📂 Archivos de Configuración

```
DAW_Practica/
├── build.xml                    ← Configuración de Ant
├── nbproject/
│   └── project.properties       ← Propiedades del proyecto
│   └── private/
│       └── private.properties   ← Config de GlassFish
└── .vscode/
    ├── tasks.json               ← Tareas de VS Code
    ├── TASKS_README.md          ← Documentación completa
    ├── REUSE_GUIDE.md           ← Guía para otros proyectos
    └── tasks-template.json      ← Plantilla reutilizable
```

---

## ⚙️ Variables de Entorno Configuradas

✅ **Ant está en PATH**: `C:\Program Files\NetbeansBinaries_27\extide\ant\bin`
✅ **Versión**: Apache Ant 1.10.14
✅ **Java**: JDK 21 (configurado en NetBeans)
✅ **Servidor**: GlassFish 7.0.14

---

## 🆘 Ayuda Rápida

- **Ver documentación completa**: Abrir `TASKS_README.md`
- **Copiar a otro proyecto**: Ver `REUSE_GUIDE.md`
- **Problemas con Ant**: Ejecutar `ant -version` en terminal
- **Ver targets disponibles**: Ejecutar `ant -projecthelp`

---

**Tip**: Presiona `Ctrl+Shift+B` para hacer build rápidamente! 🚀

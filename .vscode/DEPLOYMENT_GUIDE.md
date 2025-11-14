# 🚀 Guía Paso a Paso: Ejecutar el Proyecto en GlassFish

## ✅ Pasos para Ejecutar tu Aplicación

### **Paso 1: Verificar que GlassFish está ejecutándose**

**Opción A - Verificar puertos:**
```powershell
netstat -ano | Select-String ":4848|:8080"
```

Deberías ver:
- Puerto **4848** - Consola de administración
- Puerto **8080** - Servidor HTTP

**Opción B - Usar asadmin:**
```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" list-domains
```

Si no está ejecutándose, inícialo:
```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" start-domain domain1
```

---

### **Paso 2: Construir el Proyecto**

**Opción A - VS Code (Recomendado):**
```
Presiona: Ctrl+Shift+B
```

**Opción B - Terminal:**
```powershell
ant dist
```

Esto generará: `dist/DAW_Practica.war`

---

### **Paso 3: Desplegar la Aplicación**

#### **🎯 Método 1: Usando asadmin (Recomendado si ant run falla)**

```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" deploy --force --contextroot /app "C:\Users\Javi\Documents\GitHub\UNI\DAW_Practica\dist\DAW_Practica.war"
```

#### **🎯 Método 2: Usando ant run**

```powershell
ant run
```

**Nota**: Si ant run falla con error de XML, usa el Método 1 (asadmin).

#### **🎯 Método 3: Autodeploy (Más simple)**

```powershell
Copy-Item "dist\DAW_Practica.war" -Destination "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\domains\domain1\autodeploy\"
```

GlassFish detectará y desplegará automáticamente el archivo.

---

### **Paso 4: Verificar el Despliegue**

**Ver aplicaciones desplegadas:**
```powershell
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" list-applications
```

Deberías ver: `DAW_Practica <web>`

---

### **Paso 5: Abrir en el Navegador**

**URL de la aplicación:**
```
http://localhost:8080/app
```

(El context-root es `/app` según tu configuración en `glassfish-web.xml`)

---

## 🔄 Workflow Completo

### **Para Desarrollo Diario:**

1. **Haz cambios en el código**
2. **Build**: `Ctrl+Shift+B` o `ant dist`
3. **Deploy**: Usa una de las tareas de VS Code:
   - `Ant: Deploy with asadmin` (nueva tarea recomendada)
   - O manualmente: `asadmin deploy --force ...`
4. **Refrescar navegador**: `F5` en http://localhost:8080/app

### **Build + Deploy Rápido:**

```powershell
# Opción 1: Usando asadmin (más confiable)
ant dist; & "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" deploy --force --contextroot /app "dist\DAW_Practica.war"

# Opción 2: Usando autodeploy (más simple)
ant dist; Copy-Item "dist\DAW_Practica.war" -Destination "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\domains\domain1\autodeploy\"
```

---

## 🛠️ Comandos Útiles de GlassFish

### **Gestión del Servidor:**
```powershell
# Iniciar GlassFish
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" start-domain domain1

# Detener GlassFish
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" stop-domain domain1

# Reiniciar GlassFish
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" restart-domain domain1

# Ver estado
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" list-domains
```

### **Gestión de Aplicaciones:**
```powershell
# Listar aplicaciones
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" list-applications

# Desplegar (forzar reemplazo)
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" deploy --force --contextroot /app "dist\DAW_Practica.war"

# Desinstalar
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" undeploy DAW_Practica

# Ver logs
Get-Content "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\domains\domain1\logs\server.log" -Tail 50
```

---

## 🆘 Troubleshooting

### **Problema: "ant run" falla con error de XML**

**Causa**: Problema de parseo en `glassfish-web.xml` durante el despliegue con Ant.

**Solución**: Usa `asadmin` en lugar de `ant run`:
```powershell
ant dist
& "C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\bin\asadmin.bat" deploy --force --contextroot /app "dist\DAW_Practica.war"
```

### **Problema: No puedo acceder a http://localhost:8080/app**

**Solución**:
1. Verifica que GlassFish está ejecutándose: `netstat -ano | Select-String ":8080"`
2. Verifica que la app está desplegada: `asadmin list-applications`
3. Revisa los logs: `C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\domains\domain1\logs\server.log`

### **Problema: "Port 8080 already in use"**

**Solución**: Otro proceso está usando el puerto. Para encontrarlo:
```powershell
netstat -ano | Select-String ":8080"
# Encuentra el PID y detenlo o cambia el puerto de GlassFish
```

### **Problema: Cambios no se reflejan en la aplicación**

**Solución**:
1. Limpia el proyecto: `ant clean`
2. Build: `ant dist`
3. Despliega con `--force`: `asadmin deploy --force ...`
4. Limpia cache del navegador: `Ctrl+F5`

### **Problema: GlassFish no inicia**

**Solución**:
1. Verifica Java: `java -version` (debe ser JDK 11 o superior)
2. Revisa logs: `C:\SwUni\DAW\GlassFish_Server_7.0.14\glassfish\domains\domain1\logs\server.log`
3. Prueba reiniciar: `asadmin stop-domain domain1` y luego `asadmin start-domain domain1`

---

## 🔗 URLs Importantes

| Servicio | URL |
|----------|-----|
| **Aplicación** | http://localhost:8080/app |
| **Consola Admin** | http://localhost:4848 |
| **Usuario Admin** | admin (sin contraseña por defecto) |

---

## 📝 Notas Importantes

1. **Context Root**: Tu aplicación usa `/app` como context-root (definido en `glassfish-web.xml`)
2. **Despliegue Automático**: Puedes copiar el WAR a `autodeploy/` para despliegue automático
3. **Hot Reload**: GlassFish soporta hot reload, pero es mejor redesplegar para cambios mayores
4. **Logs**: Siempre revisa los logs si algo no funciona

---

**¡Tu aplicación está ahora ejecutándose en http://localhost:8080/app!** 🎉

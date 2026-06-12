# 🛠️ Sistema de Gestión de Herramientas - SENATI

Este es mi proyecto de aplicación móvil para el control de herramientas. La app permite registrar ingresos, buscar herramientas por ID para actualizar su estado y ver un historial completo con opción de eliminar registros directamente desde el celular.

## 📌 Funcionalidades Principales
*   **Registro (Préstamo):** Formulario para ingresar nuevas herramientas a la base de datos (Nombre, Marca, Descripción, Condición y Tipo).
*   **Recepción:** Buscador por ID que rellena automáticamente los campos para editar o actualizar herramientas ya existentes.
*   **Historial:** Una lista en tiempo real (RecyclerView) que muestra todo lo que hay en la base de datos.
*   **Eliminación:** Botón directo en el historial para borrar registros que ya no se necesiten.

## 🚀 Tecnologías que usé
*   **Android Studio:** Desarrollado en Java.
*   **Volley:** Para conectar la app con el servidor PHP.
*   **Backend:** PHP (scripts para buscar, guardar, listar y eliminar).
*   **Base de Datos:** MySQL (XAMPP).
*   **Interfaz:** Material Design con navegación por pestañas (TabLayout).

## ⚙️ Configuración del Servidor (XAMPP)
Para que la app funcione, los archivos PHP (que están en la carpeta **api_php** de este repositorio) deben estar en la carpeta `C:\xampp\htdocs\api_senati\`:

1.  `buscar.php`
2.  `guardar.php`
3.  `historial.php`
4.  `actualizar.php`
5.  `eliminar.php`

**Nota Importante:** La base de datos se llama **constructora** y la tabla principal es **herramientas**. No olvides cambiar la IP en el código de Java (`IP_SERVIDOR`) por la que te salga en el `ipconfig` de tu PC.

---
**Curso:** Desarrollo de Aplicaciones Móviles  
**Institución:** SENATI  
**2024**

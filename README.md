# 🛠️ AppSENATI - Gestión de Herramientas

![Android](https://img.shields.io/badge/Platform-Android-brightgreen.svg)
![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Gradle](https://img.shields.io/badge/Build-Gradle-blue.svg)

Una aplicación Android moderna diseñada para la gestión eficiente de préstamos y recepción de herramientas en **SENATI**.

## 🚀 Características

*   **Navegación Intuitiva:** Implementación de `TabLayout` para cambiar fácilmente entre:
    *   **Préstamo:** Registro de salida de herramientas.
    *   **Recepción:** Gestión de entrada y actualización de estado.
    *   **Historial:** Visualización de movimientos.
    *   **Configuración:** Ajustes del sistema.
*   **Conectividad en Tiempo Real:** Integración con API externa mediante **Volley** para búsqueda y actualización de herramientas.
*   **Interfaz Moderna:** Diseño basado en Material Design con bordes redondeados y colores institucionales.

## 📱 Capturas de Pantalla

| Recepción de Herramientas | Navegación |
| :---: | :---: |
| ![Recepción](https://raw.githubusercontent.com/chris222e/APP_Android/main/app/src/main/res/drawable/pcc.png) | 4 Pestañas principales |

## 🛠️ Tecnologías Utilizadas

*   **Android SDK:** Nivel 36.1 (Android 15).
*   **Lenguaje:** Java.
*   **Librerías:**
    *   `Volley`: Para peticiones HTTP (JSON).
    *   `Material Components`: Para botones, pestañas y campos de texto.
    *   `ConstraintLayout`: Para diseños flexibles.

## ⚙️ Requisitos para el Desarrollador (Backend)

Para que las funciones de búsqueda y actualización operen correctamente, se requiere un servidor local (XAMPP/WAMP) con los siguientes archivos en `htdocs/api_senati/`:

1.  `buscar.php`: Retorna un JSON con los datos de la herramienta por ID.
2.  `actualizar.php`: Recibe un POST JSON para actualizar el estado de la herramienta.

> **Nota:** La IP configurada por defecto es `10.0.2.2` (Emulador de Android). Si usa un dispositivo físico, actualice la IP en `recepcion.java`.

---
Desarrollado para el proyecto de gestión de herramientas de **SENATI**.

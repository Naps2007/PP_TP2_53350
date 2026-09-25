# PP_TP2_53350 - Trabajo Práctico N° 2

## Información del Alumno
* **Nombre:** Tomas Felman
* **Legajo:** 53350
* **Carrera:** Ingeniería en Sistemas de Información (UTN FRM)
* **Asignatura:** Paradigmas de Programación - 2026

## Descripción del Proyecto
Sistema de Gestión de Eventos Universitarios desarrollado en Java con arquitectura modular, manejo de excepciones, persistencia de datos mediante serialización y uso de genéricos.

## Funcionalidades Implementadas
* **Excepciones Personalizadas:** Validación de cupos con `CupoExcedidoException` y bloques `try-catch-finally`.
* **Persistencia por Serialización:** Guardado y recuperación del estado de eventos en archivos binarios (`.dat`).
* **Interfaces:** Implementación de `Certificable` para la emisión de certificados de asistencia.
* **Genéricos y Wildcards:** Filtrado dinámico de actividades (`<T extends Actividad>`) y cálculo de costos de materiales (`List<? extends Actividad>`).

## Captura de Ejecución en Consola
![Salida Consola](salida_consola.png)

Aplicación de consola desarrollada en **Java** que gestiona un torneo de eliminación directa de 16 equipos. El proyecto implementa la arquitectura **Modelo-Vista-Controlador (MVC)**, algoritmos recursivos para el emparejamiento de llaves, manejo de excepciones personalizadas y persistencia de datos mediante serialización binaria.

## Características Principales

* **Arquitectura MVC:** Separación clara entre la lógica de negocio (`Modelo`), la interfaz de usuario por consola (`Vista`), la gestión del flujo (`Controlador`) y el manejo de errores (`Excepcion`).
* **Sorteo Recursivo:** Emparejamiento aleatorio de los 16 equipos para los Octavos de Final utilizando recursividad.
* **Persistencia Binaria (`.ser`):** Guardado y recuperación completa del estado del torneo en el archivo `torneo.ser` mediante `ObjectOutputStream` y `ObjectInputStream`.
* **Inspección de Archivo Binario:** Opción integrada en el menú para deserializar y visualizar en consola los datos almacenados sin requerir herramientas externas.
* **Excepciones Personalizadas:** Validación de reglas de negocio mediante `NumeroEquiposInvalidoException` y `SorteoInvalidoException`.

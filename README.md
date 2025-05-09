El proyecto fue probado utilizando la base de datos en memoria H2, que permite ejecutar y validar la lógica de la aplicación sin requerir una instalación externa de PostgreSQL. Debido a limitaciones de tiempo y configuración del entorno, no se realizó una integración final con PostgreSQL, pero toda la lógica fue verificada localmente a través de la consola de H2 y pruebas con Postman.

-Ejecutar con Maven:

mvn spring-boot:run

-Ingresar a http://localhost:8080/h2-console con:

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: (vacío)

Algunos errores de ejecución, no dio tiempo para implementar todo correctamente.

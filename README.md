---Instrucciones detalladas para configurar y desplegar la aplicación.
Clonar el repositorio Git del proyecto o descargar el proyecto en un archivo Zip y descomprimirlo. Luego ingresar a la carpeta "Frontend TBD-Lab-1" y acceder al simbolo de sistema 
o Cmd para ejecutar los siguientes comandos:

```bash
npm install
npm install axios
npm install vue-router
```

> [!NOTE]  
> Sabemos que `npm install` deberia ser suficiente, sin embargo a veces igualmente fue necesario instalar por separado `axios` y `vue-router`, asi evitamos probelmas..

## Backend

Después utilizar un IDE a gusto para poder manejar Frontend y Backend del proyecto. Con esto acceder mediante el IDE seleccionado a los archivos `application.properties` y `Sql2oConfig.java` que se encuentran en  `\src\main\resources` y `\src\main\java\com\example\Ecommerce\config" respectivamente, estos se encuentran en el Backend del proyecto y se tienen que modificar credenciales de el nombre de usuario y contraseña de acceso a la base de datos en postgreSQL (Cambiar el puerto si es necesario, pero postgreSQL trabaja por defecto en el puerto 5432).

Tener creada una base de datos en postgreSQL con el nombre `Ecommerce` y utilizar el script llamado `createDB.sql` para crear la base de datos, luego utilizar `datosDump.sql` para poder cargar datos "dummy" todo esto se encuentra en la ruta del proyecto `\src\main\resources\Datos`, el cual contiene la creación de la base de datos, la creación de tablas y su llenado, además de 2 sentencias para poder visualizar los usuarios y las tareas.

Con esto queda listo para levantar el Backend, en nuestro caso utilizamos IntelliJ 

## Frontend

Para levantar el frontend, solamente basta ingresar a la carpeta `/Frontend TBD-Lab-1/` y en la consola ejectura `npm run dev` lo que lo abrira donde se especifica en la consola.

## Uso

Con lo anterior quedaria levantado (si no modifican ningun archivo ni puerto):

- Frontend en la url: http://localhost:5173/
- Backend en la url: http://localhost:8090/

Para ver el funcionamiento o para ver que los datos se estan modificando o guardando se pueden utilizar las rutas de los controladores del backend en consultas mediante Postman u otro equivalente, o de por si probar
directamente con las funcionaledades respectivas de la aplicación web.


### Documentación que explique cómo funciona la aplicación y cómo se realizó la implementación.
Al utilizar la ruta http://localhost:5173/ para acceder a la aplicación se le redireccionara a la página principal, donde:

Página principal:
- Registrarse: Funcionalidad que registra al usuario (Después redirecciona directamente a iniciar sesión).
- Iniciar sesión: Funcionalidad que permite loguear al usuario (Después redirecciona al menú del usuario logueado).

Menú usuario:
-Crear tareas: Funcionalidad que permite al usuario crear una tarea en estado "Pendiente" con los datos respectivos.
-Editar tareas: Funcionalidad que permite editar parametros de las tareas existentes del usuario o eliminar una tarea determinada.
-Marcar tareas: Funcinalidad que permite finalizar o dar por completada las tareas pendientes del usuario.
-Ver Tareas: Funcionalidad que permite ver las tareas pendientes y completadas que tenga cada uauarioa, además de poder filtrar tareas por estado o buscar tareas mediante una palabras clave determinada en el titulo 
o descripcion de las tareas.
-Notificaciones: Funcionalidad que notifica al usuario las tareas que estan protno a vencer.
-Cerrar sesión: Funcionalidad que permite hacer logout al usuario.

## Pruebas de queries SQL.

Para ejecutar las pruebas, se deben correr los archivos ubicados en `/Backend/src/main/resources/Datos/` donde se encuentra `procedureQueries.sql` que tiene los procedimientos solicitados, `sentenciaSQL.sql` y `triggerinventario.sql` junto a `triggerQueries.sql` que mantienen los triggers necesarios, adentro de cada archivo encontraran la documentacion pertinente.

En nuestro caso, para ejecutar estas distintas queries utilizamos PgAdmin 4 para copiarlas, pegarlas y poder correrlas, aunque es libre de usar la metodologia que mas le acomode.

## Lenguajes y tecnologias

Para la implementación del Backend se utilizo Springboot para la creación del proyecto, Itellij IDEA para manejar el Backend y Sql2o para la conexión entre Backend y base de datos.

Para la implementación del Frontend se utilizo Node.js y Vue para la creación del proyecto, Visual Studio Code para manejar el Frontend y Axios para poder conectar Backend y Frontend.

Y para la implementacion y manejo de la base de datos se utilizo postgreSQL y PGAdmin 4.
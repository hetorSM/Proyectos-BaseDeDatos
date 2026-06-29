# Proyectos de Base de Datos en Java

Este repositorio contiene varios proyectos de base de datos desarrollados en Java. Cada carpeta es un subproyecto independiente con una finalidad distinta: desde ejemplos de conexión JDBC hasta aplicaciones JavaFX con Hibernate.

## Proyectos incluidos

- `proyectoLogin/` - Aplicación JavaFX con pantalla de login y panel principal. Contiene controladores y vistas (`FXML`) para autenticar usuarios y navegar entre interfaces. Ideal para ver cómo integrar JavaFX con lógica de acceso a datos.
- `conectados2/` - Proyecto simple con una clase `Main.java` que muestra una estructura básica de aplicación Java. Sirve como punto de partida para conectar a una base de datos y ejecutar consultas desde una aplicación de consola o un proyecto ligero.
- `cursoBasicoFX/` - Proyecto JavaFX más completo con varias pantallas: login, panel de control, formularios para añadir y editar datos. Incluye controladores para manejar operaciones básicas de base de datos y es útil como ejemplo de CRUD en una interfaz gráfica.
- `Practica1_GuardarDatos/` - Aplicación Java que guarda datos de alumnos en archivos de texto y XML. Aunque no es un proyecto de base de datos relacional, muestra técnicas de persistencia local y manejo de formatos de archivo en Java.
- `practica4hibernate/` - Proyecto con Hibernate y JavaFX. Contiene entidades como `Usuario`, `Trabajo` y `Camion`, junto con controladores para gestionar operaciones de persistencia mediante Hibernate. Es el mejor ejemplo de uso de un ORM en este repositorio.

> Nota: Revisa cada carpeta para encontrar los detalles específicos de ese proyecto, como la base de datos usada, las dependencias y las instrucciones de ejecución. En alguno de los proyectos, tanto la base de datos como las tablas deben ser creadas. Base de datos usada **MySQL.**

## Requisitos

- Java JDK instalado (versión 11 o superior recomendada).
- Maven instalado o usar el wrapper (`mvnw`) incluido en los proyectos que lo traen.
- Motor de base de datos instalado y configurado según el proyecto, especialmente para los proyectos que usan JDBC o Hibernate.
- Se uso el ide de IntelliJ IDEA, para tener mejor facilidad de abrir los proyectos.

## Cómo ejecutar

1. Abre la carpeta del proyecto que quieres ejecutar.
2. Revisa el contenido propio de ese proyecto si necesita una base de datos con sus tablas.
3. Importa el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, NetBeans).
4. Configura la conexión a la base de datos según el archivo de configuración, `hibernate.cfg.xml` o variables de entorno.
5. Ejecuta la clase principal (`main`) del proyecto o la aplicación JavaFX.

## Notas

- Asegúrate de tener las credenciales y la base de datos correctas antes de ejecutar cualquier proyecto.
- Si el proyecto incluye scripts SQL, ejecútalos primero para crear las tablas y datos necesarios.
- Ajusta los parámetros de conexión si usas un servidor distinto o nombres de base de datos diferentes.

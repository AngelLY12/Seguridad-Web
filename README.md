CRUD DE CELULARES

Installation

Install my-project with Download zip

Al descargar el proyecto debes descomprimirlo en la carpeta Webapps de Tomcat partiendo del directorio api-crud/, ese es el directorio raíz por lo que debes omitir el directorio Seguridad-Web.
```bash
mv api-crud /usr/local/tu-tomcat/webapps
```
Run Locally

Una vez que tengas el proyecto en webapps debes tomar algunas consideraciones, primero verificar tu CLASSPATH.
```bash
echo $CLASSPATH
```
Si este aparece vacio debes incluir las apis servlet.jar y database.jar
```bash
nano /etc/profile
export CLASSPATH=/usr/local/tu-tomcat/lib/servlet-api.jar:/usr/local/tu-tomcat/webapps/api-crud/WEB-INF/lib/database.jar
source /etc/profile
```
Una vez cargadas las apis, dirigete al directorio classes del proyecto y compila los archivos.
```bash
cd /usr/local/tu-tomcat/webapps/api-crud/WEB-INF/classes
javac controllers/*.java modelo/*.java
```
Appendix

> **NOTE:**
Si tienes instalada una versión de tomcat 10 o superior no es necesario hacer modificaciones a los archivos.
En dado caso de que tu versión de tomcat sea la 9 o inferior, no es necesario cambiar algo en los controllers, en dado caso de ser una version 10 o superior, deberas entrar dentro de `./api-crud/WEB-INF/classes/controllers` y en cada controlador cambiar las librerias javax por jakarta y compilar el proyecto.

> **IMPORTANT:**
Para crear la base de datos es recomendable ejecutar los siguientes comandos dentro de tu psql:
> ```bash
> CREATE DATABASE celulares
> CREATE TABLE celular (
> imei BIGINT PRIMARY KEY,
> nombre VARCHAR(50) NOT NULL,
> marca VARCHAR(50) NOT NULL,
> anolanzamiento INT NOT NULL);
> ```

> **TIP:**
Recuerda que siempre debes compilar los archivos java cuando les haces una modificación y reiniciar tu servidor Tomcat para que los cambios se vean reflejados.

> **IMPORTANT:**
>
> Para que la API `database.jar` funcione, debes ir al directorio `/etc` y crear un archivo llamado `database.properties`:
>
> ```bash
> nano /etc/database.properties
> ```
>
> En este archivo debes agregar las líneas:
>
> ```properties
> db.driver=org.postgresql.Driver
> db.url=jdbc:postgresql://127.0.0.1/tu_base_de_datos
> db.user=tu_usuario
> db.password=tu_contraseña
> ```


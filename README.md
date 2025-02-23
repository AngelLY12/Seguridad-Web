CRUD DE CELULARES

Installation

Install my-project with Download zip

Al descargar el proyecto debes descomprimirlo en la carpeta Webapps de Tomcat partiendo del directorio crud/, ese es el directorio raíz por lo que debes omitir el directorio Seguridad-Web.

Run Locally

Ve al directorio del proyecto `cd ./crud/WEB-INF/classes`

> **IMPORTANT:**
Compilar los archivos Java.
En este directorio vas a ejecutar el comando `javac controllers/*.java interfaces/*.java modelo/*.java services/*.java connection/*.java`

Inicia el servidor

Dependiendo de si tienes tomcat iniciado con un servicio o no podras iniciarlo con systemctl start tomcat, en caso contrario tendras que ir a la carpeta bin del servido `/usr/local/tu-tomcat/bin`. Aqui ejecutaras el archivo `./startup.sh`

Environment Variables
Para correr este proyecto debes recordar tener configuradas variables de entorno, principalmente la correspondiente al servlet.

> **IMPORTANT:**
Ejecuta `echo $CLASSPATH`, si esta se encuentra vacia entra a `~/.bashrc`, aqui deberas agregar la siguiente linea.
> `export CLASSPATH=$CLASSPATH:/usr/local/tu-tomcat-/lib/servlet-api.jar`. Posterior a esto ejecutaras source `~/.bashrc`

Appendix

> **NOTE:**
Si tienes instalada una versión de tomcat 10 o superior no es necesario hacer modificaciones a los archivos.

> **IMPORTANT:**
En dado caso de que tu versión de tomcat sea la 9 o inferior deberas entrar dentro de `./crud/WEB-INF/classes/controllers` y en cada controlador cambiar las librerias jakarta por javax y compilar el proyecto con el comando del punto Run Locally.

> **IMPORTANT:**
Tambien debes recordar modificar las variables DB_USER Y DB_PASSWORD en el archivo dentro de `./crud/WEB-INF/classes/connection/connectionDB.java` y compilar el proyecto. Para crear la base de datos es recomendable ejecutar los siguientes comandos dentro de tu psql:
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
> Tambien recuerda añadir al CLASSPATH el jar en `/etc/profile` 


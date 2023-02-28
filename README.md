# Soccer-Football_backend

## **DESCRIPCIÓN DEL PROYECTO**

Este es un proyecto práctico para el desarrollo de una API REST en Java y manejando diferentes tipos de tecnologías en la que destaca principalmente **Spring**.

También se ha prácticado el manejo de **PostMan**, **Swagger** y **Docker** . Por tanto al desplegar desplegar la **ApiRest** en las properties, cambiando a perfil de "dev" para utilizar la base de datos de H2 y por otro lado usar el perfil de "prod" para utilizar la base de datos de Posgrest, para ello tendrás que realizar el siguiente comando "docker-compose up" para desplegarla con Docker.

El proyecto trata de una **ApiRest** que gestionará la parte del **Backend** de una red social para personas amantes del fútbol. Por ello, me he inspirado en redes sociales actuales como **Instagram y Facebook**.
Además de incluír una lógica de negocio de una mezcla entre ambas. Por ello te presentaré a continuación las funcionalidades que se podrán realizar en la **API**.

Se te proporcionaran dos usuarios uno con el rol de **Admin** y otro con el rol de **User**
 ### ADMIN
 - username: admin
 - password: 1234
 ### USER
 - username: ale1234
 - password: 1234
 
 #### USUARIOS Y ADMIN ####
- Subir un post (incluye una imágen y descripción)
- Publicar un comentario en un post
- Obtener sus propios posts
- Crear cuenta de usuario
- Logearse
- Modificar datos de su perfil (contraseña)

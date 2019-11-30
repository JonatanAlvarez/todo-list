## App "TODO-List" desarrollado con Java y Angular

### Descripción y contexto
- - - - - - - - - - - - - - 

Test Técnico Software Engineer

Aplicación que permite guardar y listar una/s  Entidad/es TODOs con las siguientes características:
TODO (To do, listado de tareas por hacer)
A ) ID
B) DESCRIPCION (EL TODO a hacer)
C) Estado
D) Foto/Imagen/Documento adjunta a la DESCRIPCION

Este proyecto fue generado como un test de desarrollo en Java y Angular


### Guía de usuario
- - - - - - - - - -

#### Aplicación Angular
Listar entidades TODO
 : Para listar las entidades TODOs registradas, basta con acceder a la raiz del dominio.
 
Crear entidad TODO
 : Para ingresar una nueva entidad TODO, haga click en el botón "Nuevo TODO".

Cambiar estado de entidad TODO
 : Para cambiar el estado de una entidad TODO, liste las entidades y luego haga click en la descripción de la entidad que desea actualizar.
 
#### API REST

**Listar entidades TODO**  
 Método: GET  
 Content-Type: application/json  
 URL: `http://localhost:8080/api/todos?description=&status=`  
 Respuesta: JSON  
 
**Crear entidad TODO**   
 Método: POST  
 Content-Type: multipart/form-data  
 Parámetros: `{"description": "...", "status": "...", "file": "..."}`  
 URL : `http://localhost:8080/api/todos`  
 Respuesta: JSON  
 
**Obtener archivo adjunto**   
 Método: GET   
 Content-Type: multipart/form-data  
 URL : `http://localhost:8080/api/todos/{idTodo}/file/{fileName}`  
 Respuesta: File  
 
**Obtener una entidad TODO**   
 Método: GET   
 Content-Type: application/json  
 URL : `http://localhost:8080/api/todos/{idTodo}`  
 Respuesta: JSON  
 
**Cambiar estatus de una entidad TODO**   
 Método: PUT  
 Content-Type: application/json  
 Parámetros: `{"status": "..."}`  
 URL : `http://localhost:8080/api/todos/{idTodo}`  
 Respuesta: JSON  
 
 
### Guía de instalación
- - - - - - - - - - - - 
 
#### Requerimientos
 * JDK 1.8
 * Maven 1.13
 * Angular 8.3

~~~
git clone https://github.com/JonatanAlvarez/todo-list.git
~~~
    
  
#### API REST Java
  
Importe la carpeta TODO-API a su IDE favorito.
Puede ejecutar la aplicación desde su IDE o desde la línea de comandos con Maven.
Si usa Maven, puede ejecutar la aplicación situándose en la carpeta TODO-API y ejecutar desde una consola

    mvn spring-boot:run
    
#### CLIENT ANGULAR

Ejecute `npm install` para descargar las dependencias. Al finalizar ejecute `npm start` o `ng serve --open` para correr la aplicación. Se abrirá automáticamente un navegador hacia `http://localhost:4200/`

### Autor
- - - - - - - - - 

Jontan D. Alvarez M.
  

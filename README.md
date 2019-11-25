## App "TODO-List" desarrollado con Java y Angular

### Descripción y contexto
- - - - - - - - - - - - - - 

Test Técnico Software Engineer

Aplicacion que permite guardar y listar una/s  Entidad/es TODOs con las siguientes características:
TODO (To do, listado de tareas por hacer)
A ) ID
B) DESCRIPCION (EL TODO a hacer)
C) Estado
D) Foto/Imagen/Documento adjunta a la DESCRIPCION

Este proyecto fue generado como un test de desarrollo en Java y Angular


### Guía de usuario
- - - - - - - - - -

#### Aplicacion Angular
Listar entidades TODO
 : Para listar las entidades TODOs registradas, basta con acceder a la raiz del dominio.
 
Crear entidad TODO
 : Para ingresar una nueva entidad TODO, haga click en el boton "Nuevo TODO".

Cambiar estado de entidad TODO
 : Para cambiar el estado de una entidad TODO, liste las entidades y luego haga click en la descripcion de la entidad que desea actualizar.
 
#### API REST

Listar entidades TODO
 Metodo : GET 
 Content-Type: application/json
 URL : `http://localhost:8080/api/todos?description=&status=`
 Respuesta: JSON
 
Crear entidad TODO
 Metodo : POST 
 Content-Type: multipart/form-data
 Parametros: `{"description": "...", "status": "...", "file": "..."}`
 URL : `http://localhost:8080/api/todos`
 Respuesta: JSON
 
Obtener archivo adjunto
 Metodo : GET 
 Content-Type: multipart/form-data
 URL : `http://localhost:8080/api/todos/{idTodo}/file/{fileName}`
 Respuesta: File
 
Obtener una entidad TODO
 Metodo : GET 
 Content-Type: application/json
 URL : `http://localhost:8080/api/todos/{idTodo}`
 Respuesta: JSON
 
Cambiar estatus de una entidad TODO
 Metodo : PUT 
 Content-Type: application/json
 Parametros: `{"status": "..."}`
 URL : `http://localhost:8080/api/todos/{idTodo}`
 Respuesta: JSON
 
### Guía de instalacion
- - - - - - - - - - - - 
 
#### Tecnologías usadas
 * JDK 1.8
 * Maven 1.13
 * Angular 8.3

~~~
git clone https://github.com/JonatanAlvarez/todo-list.git
~~~
    
  
#### API REST Java
  
Importe la carpeta TODO-API a su IDE favorito.
Puede ejecutar la aplicación desde su IDE o desde la línea de comandos con Maven.
Si usa Maven, puede ejecutar la aplicación situandoce en la carpeta TODO-API y ejecutar desde una consola

    mvn spring-boot:run
    
#### CLIENT ANGULAR

Ejecute `ng serve --open` para correr la aplicacion. Se abrira automaticamente un navegador hacia `http://localhost:4200/`

### Autor
- - - - - - - - - 

Jontan D. Alvarez M.
  

# Datos alumno

- Nombre: Angel Maroto Chivite
- Email Luis Vives: angel.maroto@alumno.iesluisvives.org
- GitHub Username: Sbytmacke

&nbsp;

# Enunciado (HerenciasDto)
Escritura desde mis modelos donde elegimos el formato de fichero y su porterior lectura del mismo:

(CSV, XML, y JSON en Moshi y Gson)

---


##  Diseño implementado
Arquitectura por capas, empleando el modelo-controlador

Distinguimos:
 1. Controladores: 
- Principal: donde se realizará la lectura y escritura de los ficheros
- Consultas: realizará las llamadas de las distintas consultas

2. Un repositorio del almacenaje temporal para manipular los objetos en colecciones mediante un factory de personas.

3. Por cada tipo de fichero un almacenamiento, donde se realizará la lectura y escritura de los ficheros individualmente.

Los modelos de personas he optado por partir de una clase DTO base, es una clase abstracta por lo que no se podrá instanciar en la serialización de los ficheros, por ello debemos tener en cuenta distintos métodos para filtrar si el tipo de nuestro objeto es Alumno o Profesor. (En el caso de XML no habría complejidad)


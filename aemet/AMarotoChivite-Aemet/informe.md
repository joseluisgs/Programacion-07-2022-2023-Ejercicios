# Datos alumno

- Nombre: Angel Maroto Chivite
- Email Luis Vives: angel.maroto@alumno.iesluisvives.org
- GitHub Username: Sbytmacke

&nbsp;

# Enunciado (Aemet)
Lectura y escritura de datos desde un CSV donde elegimos el formato de exportación (XML o JSON con las librerías de MOSHI o GSON).
La lectura se realizará desde 3 ficheros CSV y crearemos un CSV en conjunto.


---


##  Diseño implementado
Arquitectura por capas, empleando el modelo-controlador

Distinguimos:
1. Controladores: 
- Principal: donde se realizará la lectura y escritura de los ficheros
- Consultas: realizará las llamadas de las distintas consultas


2. Un repositorio general.


3. Un almacenamiento por cada tipo de fichero y su exportación.

El modelo que se exportará a los formatos deseados, será un mapa el cual está agrupado como clave (fecha) y como valor una clase con los atributos que corresponden a los solicitados para la exportación.

Finalmente se realizarán una serie de consultas desde el conjunto del CSV inicial.

# Datos alumno

- Nombre: Angel Maroto Chivite
- Email Luis Vives: angel.maroto@alumno.iesluisvives.org
- GitHub Username: Sbytmacke

&nbsp;

# Enunciado (Accidentes)
Lectura y escritura de datos desde un CSV donde elegimos el formato de exportación (XML o JSON con las librerías de MOSHI o GSON)

---


##  Diseño implementado
Arquitectura por capas, empleando el modelo-controlador

Distinguimos dos controladores: 
- Principal: donde se realizará la lectura y escritura de los ficheros
- Consultas: realizará las llamadas de las distintas consultas

Un repositorio general.

Por cada tipo de fichero y su exportación un almacenamiento.

Los modelos he optado por crearlos directamente como si fueran DTO, con todos sus atributos en String, para ahorrar tranformadores y manipular un único tipo de modelo.

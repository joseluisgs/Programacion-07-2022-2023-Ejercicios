# Datos alumno

- Nombre: Angel Maroto Chivite
- Email Luis Vives: angel.maroto@alumno.iesluisvives.org
- GitHub Username: Sbytmacke

&nbsp;

# Enunciado (BurguePig)
Lectura y escritura de datos desde mis modelos donde elegimos el formato de escritura y su porterior lectura del mismo:

(Binario, CSV, JSON en Moshi y Gson, Serializado, Texto Plano y XML)

---


##  Diseño implementado
Arquitectura por capas, empleando el modelo-controlador

Distinguimos:
 1. Controladores: 
- Principal: donde se realizará la lectura y escritura de los ficheros
- Consultas: realizará las llamadas de las distintas consultas

2. Repositorios de donde recuperamos la información para crear hamburguesas mediante el Patrón Factory:
- Almacenado temporal de nuestras hamburguesas
- Almacenado temporal de nuestros ingredientes

3. Por cada tipo de fichero un almacenamiento, donde se realizará la lectura y escritura de los ficheros individualmente.

Los modelos hamburguesa e ingredientes he utilizado "transformadores" es decir mappers, para transformarlos a DTO y poder manipular ese objeto DTO, en la lectura y escritura con facilidad.

package Factories

import models.*

class PersonaFactory {
    companion object {
        private var instance: PersonaFactory? = null
        fun getInstance(): PersonaFactory {
            if (instance == null) {
                instance = PersonaFactory()
            }
            return instance!!
        }
    }
    fun lista20Personas():MutableList<Persona>{
        //Tenemos que crear 20 personas donde 10% son profesores (lo pongo paso a paso para que se vea claro)
        val porcentajeProfesores = 10
        val numeroPersonas= 20
        var numeroProfesores:Int = numeroPersonas / porcentajeProfesores
        var listaPersonas:MutableList<Persona> = mutableListOf()
        val listaDeNombres:Array<String> = arrayOf("Ana","DuditaMacUsuario","Jose","Pepe","JoseMaria","Monica","Emilio",
            "Mariano","Nemo","Pocholo","Nicolas","Gunter","Biörk","ZZTopmate","Perseo","Helios","Romulo","Ciceron",
            "JoseLuis","Sara","Lurdes","Julia","Leonor","Borja","Manuel","Teresa","Gorka","Ulises","Matilde","Alarico",
            "Ataúlfo","Sigérico","Walia","Teodorico","Turismundo","Eurico","Gesaleico","Amalarico","Theudis","Theudisclo",
            "Agila","Atanagildo","Liuva","Leovigildo","Recaredo","Witérico","Gundemaro","Sisebuto","Suíntila","Sisenando",
            "Khíntila","Tulga","Khindasvinto","Recesvinto","Wamba")
        //creamos los profesores
        while(numeroProfesores>0){
            val profesor = Profesor(modulo= NombreModulos.values().random().toString(),
                                    nombre =listaDeNombres.random() )
            listaPersonas.add(profesor)
            numeroProfesores-=1
        }
        //creamos los alumnos
        while (listaPersonas.size<20){
            val alumno = Alumno(edad = (18..40).random(),
                nombre =listaDeNombres.random() )
            listaPersonas.add(alumno)
        }
        return listaPersonas
    }

}
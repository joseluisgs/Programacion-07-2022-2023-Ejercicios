package factories

import kotlin.random.Random

fun getRdnNombre(arry: Array<String>): String{
    return arry.random()
}

fun getRdnPrecio(intRange: IntRange): Float{
    return intRange.random() + Random.nextFloat()
}
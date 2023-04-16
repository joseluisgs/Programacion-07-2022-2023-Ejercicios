package locate

import java.text.NumberFormat
import java.util.*

fun Float.toLocalMoney(): String{
    return NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(this)
}

fun String.moneyToFloat(): Float{
    val paco = this.replace("€", "").replace(',', '.').trim()
    return paco.toFloat().round()
}

fun Float.round(decimals: Int = 2): Float{
    var multiplier = 1.0f
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

fun Double.toLocalMoney(): String{
    return NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(this)
}
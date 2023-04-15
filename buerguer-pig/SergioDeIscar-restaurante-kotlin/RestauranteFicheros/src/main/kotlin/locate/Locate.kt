package locate

import java.text.NumberFormat
import java.util.*

fun Float.toLocalMoney(): String{
    return NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(this)
}

fun String.moneyToFloat(): Float{
    val paco = this.replace("€", "").replace(',', '.').trim()
    return paco.toFloat()
}

fun Double.toLocalMoney(): String{
    return NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(this)
}
package locate

import java.text.NumberFormat
import java.util.*

fun Float.toLocalMoney(): String{
    return NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(this)
}
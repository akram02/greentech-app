package tech.coderhub.basic.helper

fun Double.formatPrecession(): String {
    val price = this
    val priceL = price.toLong()
    val priceD = priceL.toDouble()
    return String.format(if (price==priceD) "%.0f" else "%.2f", price)
}
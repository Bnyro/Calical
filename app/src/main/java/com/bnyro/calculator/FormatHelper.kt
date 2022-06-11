package com.bnyro.calculator

import org.mariuszgromada.math.mxparser.Expression

fun getResult(userExp: String): String {
    val exp = Expression(userExp)
    val resultTemp: Double = exp.calculate()
    return formatNum(resultTemp, 10)
}

private fun formatNum(number: Double, maxLength: Int): String {
    var out = ""
    for (i in 0 until maxLength) {
        out = String.format("%." + i + "G", number).replace(",", ".")
        if (out.length == maxLength) {
            while (out.endsWith("0")) out = out.substring(0, out.length - 1)
            if (out.endsWith(".")) out = out.substring(0, out.length - 1)
            return out
        }
    }
    return out
}

fun countChar(str: String, c: Char): Int {
    var count = 0
    for (element in str) {
        if (element == c) count++
    }
    return count
}

package com.bnyro.calculator

fun getResult(userExp: String): String {
    val exp = userExp
        .replace("π", Math.PI.toString())
        .replace("e", Math.E.toString())
    val resultTemp = MathParser.eval(exp)
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

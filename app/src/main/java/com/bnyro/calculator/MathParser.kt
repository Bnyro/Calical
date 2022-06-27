package com.bnyro.calculator

import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

object MathParser {
    fun eval(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) return Double.NaN
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // addition
                    else if (eat('-'.code)) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.code)) x *= parseFactor() // multiplication
                    else if (eat('/'.code)) x /= parseFactor() // division
                    else if (eat('#'.code)) x %= parseFactor()
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return +parseFactor() // unary plus
                if (eat('-'.code)) return -parseFactor() // unary minus
                var x: Double
                val startPos = pos
                if (eat('('.code)) { // parentheses
                    x = parseExpression()
                    if (!eat(')'.code)) return Double.NaN
                } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) { // numbers
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.code && ch <= 'z'.code) { // functions
                    while (ch >= 'a'.code && ch <= 'z'.code) nextChar()
                    val func = str.substring(startPos, pos)
                    if (eat('('.code)) {
                        x = parseExpression()
                        if (!eat(')'.code)) return Double.NaN
                    } else {
                        x = parseFactor()
                    }
                    x =
                        when (func) {
                            "sqrt" -> sqrt(x)
                            "abs" -> abs(x)
                            "sin" -> sin(Math.toRadians(x))
                            "cos" -> cos(Math.toRadians(x))
                            "tan" -> tan(Math.toRadians(x))
                            "asin" -> asin(x)
                            "acos" -> acos(x)
                            "atan" -> atan(x)
                            "ln" -> ln(x)
                            else -> return Double.NaN
                        }
                } else {
                    return Double.NaN
                }
                try {
                    if (eat('^'.code)) x = x.pow(parseFactor()) // exponentiation
                    else if (eat('%'.code)) x /= 100 // percentage
                    else if (eat('!'.code)) x = factorial(x.toInt()) // factorial
                } catch (e: Exception) {
                    return Double.NaN
                }
                return x
            }

            fun factorial(number: Int): Double {
                var result = 1.0
                for (factor in 2..number) {
                    result *= factor.toDouble()
                }
                return result
            }
        }.parse()
    }
}

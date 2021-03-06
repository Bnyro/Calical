package com.bnyro.calical

/*
I created this as a central place for storing all of the calculator arguments as a better design practice.

If you can think of a better name for 'CalculatorArgument', feel free to change it.
 */

enum class CalculatorArgument(val stringValue: String) {
    Zero("0"),
    One("1"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    SquareRoot("sqrt("),
    Exponent("^"),
    Divide("/"),
    Multiply("*"),
    Add("+"),
    Subtract("-"),
    DecimalPoint("."),
    Percent("%"),
    ParOpen("("),
    ParClosed(")"),
    Sin("sin("),
    Cos("cos("),
    Tan("tan("),
    ArcSin("asin("),
    ArcCos("acos("),
    ArcTan("atan("),
    LogBaseE("ln("),
    EConstant("e"),
    PIConstant("π"),
    Factorial("!"),
    Absolute("abs("),
    Modulus("#")
}

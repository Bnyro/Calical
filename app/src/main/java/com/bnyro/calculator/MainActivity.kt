package com.bnyro.calculator

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.DynamicColors
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private var editText: EditText? = null
    private var resultTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.input)
        resultTextView = findViewById(R.id.result)
        val arrowTextView = findViewById<TextView>(R.id.arrowTextView)

        arrowTextView.setOnClickListener { _ ->

        }

        editText?.showSoftInputOnFocus = false
    }

    private fun updateText(strToAdd: String) {
        val oldStr = editText!!.text.toString()
        val cursorPos = editText!!.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)
        editText!!.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
        editText!!.setSelection(cursorPos + strToAdd.length)
        val resultString = result
        if (resultString != "NaN") resultTextView!!.text = resultString
    }

    fun equalsBTN(view: View?) {
        val resultString = result
        if (resultString != "NaN") {
            editText!!.setText(resultString)
            editText!!.setSelection(resultString.length)
            resultTextView!!.text = ""
        } else {
            Toast.makeText(
                applicationContext,
                getString(R.string.invalid_input),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun deleteBTN(view: View?) {
        val cursorPos = editText!!.selectionStart
        val textLen = editText!!.text.length
        if (cursorPos != 0 && textLen != 0) {
            val selection = editText!!.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            editText!!.text = selection
            editText!!.setSelection(cursorPos - 1)
            val resultString = result
            resultTextView?.text = if (resultString != "NaN") resultString else ""
        }
    }

    private val result: String
        get() {
            val userExp = editText!!.text.toString()
            val exp = Expression(userExp)
            val result: Double = exp.calculate()
            var resultString = result.toString()
            if (resultString.endsWith(".0")) resultString =
                resultString.substring(0, resultString.length - 2)
            return resultString
        }

    fun clearBTN(view: View?) {
        editText!!.setText("")
        resultTextView!!.text = ""
    }

    fun countChar(str: String, c: Char): Int {
        var count = 0
        for (element in str) {
            if (element == c) count++
        }
        return count
    }

    // default buttons
    fun zeroBTN(view: View?) {
        updateText("0")
    }

    fun oneBTN(view: View?) {
        updateText("1")
    }

    fun twoBTN(view: View?) {
        updateText("2")
    }

    fun threeBTN(view: View?) {
        updateText("3")
    }

    fun fourBTN(view: View?) {
        updateText("4")
    }

    fun fiveBTN(view: View?) {
        updateText("5")
    }

    fun sixBTN(view: View?) {
        updateText("6")
    }

    fun sevenBTN(view: View?) {
        updateText("7")
    }

    fun eightBTN(view: View?) {
        updateText("8")
    }

    fun nineBTN(view: View?) {
        updateText("9")
    }

    fun squareRootBTN(view: View?) {
        updateText("sqrt(")
    }

    fun exponentBTN(view: View?) {
        updateText("^")
    }

    fun divideBTN(view: View?) {
        updateText("/")
    }

    fun multiplyBTN(view: View?) {
        updateText("*")
    }

    fun addBTN(view: View?) {
        updateText("+")
    }

    fun subtractBTN(view: View?) {
        updateText("-")
    }

    fun pointBTN(view: View?) {
        updateText(".")
    }

    fun percentBTN(view: View?) {
        updateText("%")
    }

    // advanced buttons
    fun parBTN(view: View?) {
        val currentInput = editText?.text.toString()
        if (countChar(currentInput, "(".first()) > countChar(currentInput, ")".first())) {
            updateText(")")
        } else {
            updateText("(")
        }
    }

    fun parOpenBTN(view: View?) {
        updateText("(")
    }

    fun parClosedBTN(view: View?) {
        updateText(")")
    }

    fun sinBTN(view: View?) {
        updateText("sin(")
    }

    fun cosBTN(view: View?) {
        updateText("cos(")
    }

    fun tanBTN(view: View?) {
        updateText("tan(")
    }

    fun logBTN(view: View?) {
        updateText("log10(")
    }

    fun lnBTN(view: View?) {
        updateText("ln(")
    }

    fun eBTN(view: View?) {
        updateText("e")
    }

    fun piBTN(view: View?) {
        updateText("pi")
    }

    fun facultyBTN(view: View?) {
        updateText("!")
    }

    fun invBTN(view: View?) {

    }

    fun absBTN(view: View?) {
        updateText("abs(")
    }
}

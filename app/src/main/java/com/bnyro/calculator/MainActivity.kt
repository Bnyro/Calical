package com.bnyro.calculator

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bnyro.calculator.databinding.ActivityMainBinding
import com.google.android.material.color.DynamicColors
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val maxLength = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.input.showSoftInputOnFocus = false
    }

    private fun updateText(strToAdd: String) {
        val oldStr = binding.input.text.toString()
        val cursorPos = binding.input.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)
        binding.input.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
        binding.input.setSelection(cursorPos + strToAdd.length)
        val resultString = getResult()
        if (resultString != "NAN") binding.result.text = resultString
    }

    fun equalsBTN(view: View?) {
        val resultString = getResult()
        if (resultString != "NAN") {
            binding.input.setText(resultString)
            binding.input.setSelection(resultString.length)
            binding.result.text = ""
        } else {
            Toast.makeText(
                applicationContext,
                getString(R.string.invalid_input),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun deleteBTN(view: View?) {
        val cursorPos = binding.input.selectionStart
        val textLen = binding.input.text.length
        if (cursorPos != 0 && textLen != 0) {
            val selection = binding.input.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            binding.input.text = selection
            binding.input.setSelection(cursorPos - 1)
            val resultString = getResult()
            binding.result.text = if (resultString != "NaN") resultString else ""
        }
    }

    fun calcToggle(view: View?) {
        if (binding.calcToggle.visibility == View.GONE) {
            binding.calcToggle.visibility = View.VISIBLE
            binding.arrow.animate().setDuration(200).rotationX(180f).start()
        } else {
            binding.calcToggle.visibility = View.GONE
            binding.arrow.animate().setDuration(300).rotationX(0f).start()
        }
    }

    private fun getResult(): String {
        val userExp = binding.input.text.toString()
        val exp = Expression(userExp)
        val resultTemp: Double = exp.calculate()
        return formatNum(resultTemp)
    }

    private fun formatNum(number: Double): String {
        var out = ""
        for (i in 0 until maxLength) {
            out = String.format("%." + i + "G", number)
            if (out.length == maxLength) {
                while (out.endsWith("0")) out = out.substring(0, out.length - 1)
                if (out.endsWith(".")) out = out.substring(0, out.length - 1)
                return out
            }
        }
        return out
    }

    fun clearBTN(view: View?) {
        binding.input.setText("")
        binding.result.text = ""
    }

    private fun countChar(str: String, c: Char): Int {
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
        val currentInput = binding.input.text.toString()
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

    fun arcSinBTN(view: View?) {
        updateText("arcsin(")
    }

    fun arcCosBTN(view: View?) {
        updateText("arccos(")
    }

    fun arcTanBTN(view: View?) {
        updateText("arctan(")
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

    fun absBTN(view: View?) {
        updateText("abs(")
    }

    fun invBTN(view: View?) {
        binding.trigonometric.visibility = View.GONE
        binding.trigonometricInverted.visibility = View.VISIBLE
    }

    fun invInvertedBTN(view: View?) {
        binding.trigonometric.visibility = View.VISIBLE
        binding.trigonometricInverted.visibility = View.GONE
    }
}

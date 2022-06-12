package com.bnyro.calculator

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bnyro.calculator.databinding.ActivityMainBinding
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.input.showSoftInputOnFocus = false
    }

    private fun updateText(calculatorArgument: CalculatorArgument) {
        val oldStr = binding.input.text.toString()
        val cursorPos = binding.input.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightStr = oldStr.substring(cursorPos)
        binding.input.setText(String.format("%s%s%s", leftStr, calculatorArgument.stringValue, rightStr))
        binding.input.setSelection(cursorPos + calculatorArgument.stringValue.length)
        val resultString = getResult(binding.input.text.toString())
        if (resultString != "NAN") binding.result.text = resultString
    }

    fun equalsBTN(view: View?) {
        val resultString = getResult(binding.input.text.toString())
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
            val resultString = getResult(binding.input.text.toString())
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

    fun clearBTN(view: View?) {
        binding.input.setText("")
        binding.result.text = ""
    }

    // default buttons
    fun zeroBTN(view: View?) {
        updateText(CalculatorArgument.Zero)
    }

    fun oneBTN(view: View?) {
        updateText(CalculatorArgument.One)
    }

    fun twoBTN(view: View?) {
        updateText(CalculatorArgument.Two)
    }

    fun threeBTN(view: View?) {
        updateText(CalculatorArgument.Three)
    }

    fun fourBTN(view: View?) {
        updateText(CalculatorArgument.Four)
    }

    fun fiveBTN(view: View?) {
        updateText(CalculatorArgument.Five)
    }

    fun sixBTN(view: View?) {
        updateText(CalculatorArgument.Six)
    }

    fun sevenBTN(view: View?) {
        updateText(CalculatorArgument.Seven)
    }

    fun eightBTN(view: View?) {
        updateText(CalculatorArgument.Eight)
    }

    fun nineBTN(view: View?) {
        updateText(CalculatorArgument.Nine)
    }

    fun squareRootBTN(view: View?) {
        updateText(CalculatorArgument.SquareRoot)
    }

    fun exponentBTN(view: View?) {
        updateText(CalculatorArgument.Exponent)
    }

    fun divideBTN(view: View?) {
        updateText(CalculatorArgument.Divide)
    }

    fun multiplyBTN(view: View?) {
        updateText(CalculatorArgument.Multiply)
    }

    fun addBTN(view: View?) {
        updateText(CalculatorArgument.Add)
    }

    fun subtractBTN(view: View?) {
        updateText(CalculatorArgument.Subtract)
    }

    fun pointBTN(view: View?) {
        updateText(CalculatorArgument.DecimalPoint)
    }

    fun percentBTN(view: View?) {
        updateText(CalculatorArgument.Percent)
    }

    // advanced buttons
    fun parBTN(view: View?) {
        val currentInput = binding.input.text.toString()
        if (countChar(currentInput, "(".first()) > countChar(currentInput, ")".first())) {
            updateText(CalculatorArgument.ParClosed)
        } else {
            updateText(CalculatorArgument.ParOpen)
        }
    }

    fun parOpenBTN(view: View?) {
        updateText(CalculatorArgument.ParOpen)
    }

    fun parClosedBTN(view: View?) {
        updateText(CalculatorArgument.ParClosed)
    }

    fun sinBTN(view: View?) {
        updateText(CalculatorArgument.Sin)
    }

    fun cosBTN(view: View?) {
        updateText(CalculatorArgument.Cos)
    }

    fun tanBTN(view: View?) {
        updateText(CalculatorArgument.Tan)
    }

    fun arcSinBTN(view: View?) {
        updateText(CalculatorArgument.ArcSin)
    }

    fun arcCosBTN(view: View?) {
        updateText(CalculatorArgument.ArcCos)
    }

    fun arcTanBTN(view: View?) {
        updateText(CalculatorArgument.ArcTan)
    }

    fun logBTN(view: View?) {
        updateText(CalculatorArgument.LogBaseTen)
    }

    fun lnBTN(view: View?) {
        updateText(CalculatorArgument.LogBaseE)
    }

    fun eBTN(view: View?) {
        updateText(CalculatorArgument.EConstant)
    }

    fun piBTN(view: View?) {
        updateText(CalculatorArgument.PIConstant)
    }

    fun facultyBTN(view: View?) {
        updateText(CalculatorArgument.Factorial)
    }

    fun absBTN(view: View?) {
        updateText(CalculatorArgument.Absolute)
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

package com.nazlasalsabila.tipkalkulator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var etBillAmount: EditText
    private lateinit var spTipPercentage: Spinner
    private lateinit var switchRound: SwitchCompat
    private lateinit var tvTipAmount: TextView

    private val tipValues = arrayOf(0.15, 0.18, 0.20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBillAmount = findViewById(R.id.etBillAmount)
        spTipPercentage = findViewById(R.id.spTipPercentage)
        switchRound = findViewById(R.id.switchRound)
        tvTipAmount = findViewById(R.id.tvTipAmount)

        val tipLabels = arrayOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipLabels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipPercentage.adapter = adapter

        spTipPercentage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateTip()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        switchRound.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }

        etBillAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun calculateTip() {
        val amount = etBillAmount.text.toString().toDoubleOrNull() ?: 0.0
        val percent = tipValues[spTipPercentage.selectedItemPosition]
        var tip = amount * percent

        if (switchRound.isChecked) {
            tip = ceil(tip)
        }

        tvTipAmount.text = "Tip Amount: Rp %.2f".format(tip)
    }
}
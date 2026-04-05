package com.nazlasalsabila.dicerollxml

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView
    private lateinit var btnRoll: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice1 = findViewById(R.id.dice1)
        dice2 = findViewById(R.id.dice2)
        btnRoll = findViewById(R.id.btnRoll)
        tvResult = findViewById(R.id.tvResult)

        btnRoll.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val result1 = (1..6).random()
        val result2 = (1..6).random()

        updateDiceImage(dice1, result1)
        updateDiceImage(dice2, result2)

        tvResult.text = if (result1 == result2) {
            "Selamat, anda dapat dadu double!"
        } else {
            "Anda belum beruntung!"
        }
    }

    private fun updateDiceImage(imageView: ImageView, value: Int) {
        val imageRes = when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        imageView.setImageResource(imageRes)
    }
}
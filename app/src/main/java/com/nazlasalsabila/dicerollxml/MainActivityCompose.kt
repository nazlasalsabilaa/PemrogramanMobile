package com.nazlasalsabila.dicerollxml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceApp()
        }
    }
}

@Composable
fun DiceApp() {
    var dice1 by remember { mutableStateOf(1) }
    var dice2 by remember { mutableStateOf(1) }
    var resultText by remember { mutableStateOf("Klik Roll") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0B14)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // DADU
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = getDiceImage(dice1)),
                contentDescription = "Dice 1",
                modifier = Modifier.size(190.dp)
            )

            Image(
                painter = painterResource(id = getDiceImage(dice2)),
                contentDescription = "Dice 2",
                modifier = Modifier.size(190.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // BUTTON
        Button(
            onClick = {
                dice1 = (1..6).random()
                dice2 = (1..6).random()

                resultText = if (dice1 == dice2) {
                    "Selamat, anda dapat dadu double!"
                } else {
                    "Anda belum beruntung!"
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF824CC9)
            )
        ) {
            Text(
                text = "Roll",
                color = Color.Black,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // HASIL
        Text(
            text = resultText,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

// FUNCTION GAMBAR DADU
fun getDiceImage(value: Int): Int {
    return when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}
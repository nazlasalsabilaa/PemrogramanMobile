package com.nazlasalsabila.tipkalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

class MainActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipApp()
        }
    }
}

@Composable
fun TipApp() {

    var bill by remember { mutableStateOf("") }
    var tipIndex by remember { mutableStateOf(0) }
    var roundUp by remember { mutableStateOf(false) }

    val tips = listOf(15, 18, 20)

    val amount = bill.toDoubleOrNull() ?: 0.0
    var tip = amount * tips[tipIndex] / 100
    if (roundUp) tip = ceil(tip)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(20.dp)
    ) {

        Text(
            "Calculate Tip",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        // BILL BOX
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(25.dp)
                    .border(1.dp, Color(0xFF5D4037)),
                contentAlignment = Alignment.Center
            ) {
                Text("100", fontSize = 10.sp, color = Color(0xFF5D4037))
            }

            Spacer(Modifier.width(10.dp))

            TextField(
                value = bill,
                onValueChange = { bill = it },
                placeholder = { Text("Bill Amount") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )
        }

        Spacer(Modifier.height(20.dp))

        // TIP BOX
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(1.dp, Color.LightGray)
                .padding(10.dp)
        ) {

            Text(
                "Tip Percentage",
                fontSize = 12.sp,
                color = Color(0xFF5D4037),
                modifier = Modifier.padding(start = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    "%",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037),
                    modifier = Modifier.width(30.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                tips.forEachIndexed { index, value ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = tipIndex == index,
                            onClick = { tipIndex = index }
                        )
                        Text("$value%")
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // SWITCH
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Round up tip?",
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.weight(1f))

            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }

        Spacer(Modifier.height(30.dp))

        // RESULT
        Text(
            text = "Tip Amount: $${"%.2f".format(tip)}",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}
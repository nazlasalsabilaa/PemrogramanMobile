package com.nazlasalsabila.tipkalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipApp() {
    var bill by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val tips = listOf(15, 18, 20)
    var selectedTip by remember { mutableStateOf(tips[0]) }
    var roundUp by remember { mutableStateOf(false) }

    val amount = bill.toDoubleOrNull() ?: 0.0
    var tipResult = amount * selectedTip / 100
    if (roundUp) tipResult = ceil(tipResult)

    val purpleNazla = Color(0xFF7B1FA2)
    val customBrown = Color(0xFF5D4037)
    val darkerGrayBg = Color(0xFFE0E0E0)
    val softBlack = Color(0xFF333333)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(20.dp)
    ) {
        Text(
            text = "Calculate Tip",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = softBlack
        )

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = bill,
            onValueChange = { bill = it },
            label = {
                Text(
                    "Bill Amount",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                )
            },
            leadingIcon = {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(24.dp)
                        .border(1.5.dp, customBrown, RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("100", fontSize = 10.sp, color = customBrown, fontWeight = FontWeight.Bold)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(darkerGrayBg, RoundedCornerShape(8.dp)),
            textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = purpleNazla,
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = purpleNazla,
                unfocusedLabelColor = customBrown,
                cursorColor = purpleNazla,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Spacer(Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "$selectedTip%",
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        "Tip Percentage",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                    )
                },
                leadingIcon = {
                    Text(
                        "%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = customBrown,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(darkerGrayBg, RoundedCornerShape(8.dp)),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = purpleNazla,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = purpleNazla,
                    unfocusedLabelColor = customBrown,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                tips.forEach { tipOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "$tipOption%",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        onClick = {
                            selectedTip = tipOption
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Round up tip?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = softBlack
            )
            Spacer(Modifier.weight(1f))
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = purpleNazla,
                    checkedTrackColor = purpleNazla.copy(alpha = 0.5f)
                )
            )
        }

        Spacer(Modifier.height(48.dp))

        Text(
            text = "Tip Amount: $${"%.2f".format(tipResult)}",
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
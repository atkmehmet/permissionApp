package com.example.permissionapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun mainScreen(){
    var ad by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Ortak modifier tanımladık
    val ortakInputModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Kullanıcı Bilgileri", fontSize = 20.sp)

        addEdt(
            value = ad,
            onValueChange = { ad = it },
            label = "Ad Soyad",
            helperText = "Tam adınızı girin",
            modifier = ortakInputModifier
        )

        addEdt(
            value = email,
            onValueChange = { email = it },
            label = "E-posta",
            helperText = "Geçerli bir e-posta girin",
            modifier = ortakInputModifier
        )
    }
}
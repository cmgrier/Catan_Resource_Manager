package com.example.catanResourceManager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catanResourceManager.ui.theme.Primary

@Preview
@Composable
fun testGround() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf(TextData()) }
        if (name.name.isNotEmpty()) {
            Text(
                text = "Hello, ${name.name}!",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        OutlinedTextField(
            value = name.name,
            onValueChange = { name.name = it },
            label = { Text("Name") }
        )
    }
}

class TextData() {
    var name by mutableStateOf("")
}
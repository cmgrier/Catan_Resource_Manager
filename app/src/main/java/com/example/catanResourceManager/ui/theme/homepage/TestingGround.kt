package com.example.catanResourceManager.ui.theme.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catanResourceManager.ui.theme.Primary

@Preview
@Composable
fun testGround() {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.background(Primary).width(100.dp).height(100.dp)
    ) {
        BasicText(text = "Text1", modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically).weight(1f))
        BasicText(text = "Text2", modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically))
    }
}
package com.example.catanResourceManager

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.catanResourceManager.ui.theme.CatanResourceManagerTheme
import com.example.catanResourceManager.ui.theme.*
import com.example.catanResourceManager.ui.theme.homepage.NumberList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatanResourceManagerTheme {
                // A surface container using the 'background' color from the theme
                MainApp ()
            }
        }
    }
}

@Composable
fun MainApp() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Text(text = "drawerContent") },
        content = { FrontPage() },
        bottomBar = {  }
    )
}

@Composable
fun FrontPage() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DiceView(rollManager = RollManager(mutableListOf()), modifier = Modifier.align(Alignment.TopStart))
        HandView(modifier = Modifier.align(Alignment.BottomStart))
    }
}
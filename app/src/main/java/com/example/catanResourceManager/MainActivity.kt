package com.example.catanResourceManager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.catanResourceManager.Player.PlayerManager
import com.example.catanResourceManager.ui.theme.CatanResourceManagerTheme

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
    val playerManager = PlayerManager()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { ResourceEditor(playerManager.numberManager) },
        content = { FrontPage(playerManager) },
        bottomBar = {  }
    )
}

@Composable
fun FrontPage(playerManager: PlayerManager) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DiceView(rollManager = RollManager(mutableListOf(playerManager)), modifier = Modifier.align(Alignment.TopStart))
        HandView(modifier = Modifier.align(Alignment.BottomStart), playerManager)
    }
}
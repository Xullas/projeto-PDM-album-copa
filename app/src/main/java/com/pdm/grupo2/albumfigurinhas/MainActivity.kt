package com.pdm.grupo2.albumfigurinhas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdm.grupo2.albumfigurinhas.ui.screens.CompetitionScreen
import com.pdm.grupo2.albumfigurinhas.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 412.dp, height = 917.dp)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        AppMainContent()
                    }
                }
            }
        }
    }
}

@Composable
fun AppMainContent() {
    CompetitionScreen(onTeamSelect = { countryId ->
        println("Seleção selecionada: $countryId")
    })
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MyApplicationTheme {
        Box(
            modifier = Modifier
                .size(width = 412.dp, height = 917.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            AppMainContent()
        }
    }
}
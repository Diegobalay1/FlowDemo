package com.example.flowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowdemo.ui.theme.FlowDemoTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: DemoViewModel = viewModel()) {
    //MainScreen(viewModel.myFlow)
    MainScreen(viewModel.newFlow)
}

@Composable
//fun MainScreen(flow: Flow<Int>) {
fun MainScreen(flow: Flow<String>) {
    //val count by flow.collectAsState(initial = 0)
    val count by flow.collectAsState(initial = "Current value =")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count", style = TextStyle(fontSize = 40.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlowDemoTheme {
        ScreenSetup(viewModel())
    }
}
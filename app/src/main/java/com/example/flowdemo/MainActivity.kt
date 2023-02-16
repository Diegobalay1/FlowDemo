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
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

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
                    ScreenSetup()
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: DemoViewModel = viewModel()) {
    //MainScreen(viewModel.myFlow)
    //MainScreen(viewModel.newFlow)
    MainScreen(viewModel)
}

@Composable
fun MainScreen(viewModel: DemoViewModel) {
//fun MainScreen(flow: Flow<Int>) {
//fun MainScreen(flow: Flow<String>) {
    //val count by flow.collectAsState(initial = 0)
    //val count by flow.collectAsState(initial = "Current value =")
    //var count by remember { mutableStateOf<String>("Current value =") }
    //var count by remember { mutableStateOf<Int>(0) }
    var count by remember { mutableStateOf<String>("") }

    LaunchedEffect(Unit) {
        val flow1 = (1..5).asFlow()
            .onEach { delay(1000) }
        val flow2 = flowOf("one", "two", "three", "four")
            .onEach { delay(1500) }
        flow1.zip(flow2) { value, string -> "$value, $string" }
            .collect { count = it }
        /*flow1.combine(flow2) { value, string -> "$value, $string" }
            .collect { count = it }*/
    }

    /*LaunchedEffect(key1 = Unit, block = {
        flow.collect {
            count = it
        }
    })*/
    /*LaunchedEffect(Unit) {
        try {
            flow.collect {
                count = it
            }
        } finally {
            count = "Flow stream ended."
        }
    }*/
    /*LaunchedEffect(Unit) {
        val elapsedTime = measureTimeMillis {
            flow
                .buffer()
                .collect {
                    count = it
                    delay(1000)
            }
        }
        count = "Duration = $elapsedTime"
    }*/
    /*LaunchedEffect(Unit) {
        flow
            //.reduce { accumulator, value ->
            .fold(initial = 10) { accumulator, value ->
                count = accumulator
                accumulator + value
            }
    }*/

    /*LaunchedEffect(Unit) {
        viewModel.myFlowflattening
            //.flatMapConcat { viewModel.doubleIt(it) }
            .flatMapMerge { viewModel.doubleIt(it) }
            .collect {
                count = it
                println("Count = $it")
            }
    }*/

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
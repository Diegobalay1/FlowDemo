package com.example.flowdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class DemoViewModel : ViewModel() {

    val myFlow: Flow<Int> = flow {
        // Producer block
        for (i in 0..9) {
            emit(i)
            delay(2000)
        }
    }

}
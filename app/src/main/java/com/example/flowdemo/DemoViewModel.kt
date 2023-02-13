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

    val newFlow = myFlow
        /*.filter {
            it % 2 == 0
        }
        .map {
            "Current value = $it"
        }*/
        /*.transform {
            emit("Value = $it")
            delay(1000)
            val doubled = it * 2
            emit("Value doubled = $doubled")
        }*/
        .map {
            "Current value = $it"
        }
}
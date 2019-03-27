package com.example.estudoandroid.chronometerld

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class LiveDataTimerViewModel: ViewModel() {

    //final
    //MutableLiveData chama o super
    val elapsedTime = MutableLiveData<Long>()

    init {
        val initialTime = SystemClock.elapsedRealtime()
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000

                //postValue por que está numa thread separada
                elapsedTime.postValue(newValue)
            }
        }, 1000, 1000)
    }


}
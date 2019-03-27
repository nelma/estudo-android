package com.example.estudoandroid.chronometerld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.estudoandroid.R
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    //by lazy por que precisa do contexto pra inicializar
    private val liveDataTimerViewModel by lazy {
        ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        subscribe()
    }

    fun subscribe() {
        val elapsedTimeObserver = Observer<Long> {time ->
            textView.text = "$time.seconds"
        }

        liveDataTimerViewModel.elapsedTime.observe(this, elapsedTimeObserver)
    }
}

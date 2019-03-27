package com.example.estudoandroid.seekbar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeekBarViewModel: ViewModel() {


    //final get
    val seekBarValue = MutableLiveData<Int>()
}
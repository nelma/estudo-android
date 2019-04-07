package com.example.estudoandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.estudoandroid.entities.Word
import com.example.estudoandroid.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WordRepository(application)

    val allWords = repository.allWords

    fun insert(word: Word) {
        repository.insert(word)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun delete(word: Word) {
        repository.delete(word)
    }
}
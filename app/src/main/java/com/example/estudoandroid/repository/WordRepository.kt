package com.example.estudoandroid.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.estudoandroid.dao.WordDao
import com.example.estudoandroid.db.WordRoomDatabase
import com.example.estudoandroid.entities.Word
import org.jetbrains.anko.doAsync

class WordRepository(application: Application) {

    private val wordDao: WordDao

    val allWords: LiveData<List<Word>>

    //inicializar algo após o constructor
    init {
        val db = WordRoomDatabase.getDatabase(application)
        wordDao = db.wordDao()

        allWords = wordDao.getAllWords()
    }

    fun insert(word: Word) {
        doAsync {
            wordDao.insert(word)
        }
    }

    fun deleteAll() {
        doAsync {
            wordDao.deleteAll()
        }
    }

    fun delete(word: Word) {
        doAsync {
            wordDao.delete(word)
        }
    }

    fun update(word: Word) {
        doAsync {
            wordDao.update(word)
        }
    }
}
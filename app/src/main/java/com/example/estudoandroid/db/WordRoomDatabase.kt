package com.example.estudoandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.estudoandroid.dao.WordDao
import com.example.estudoandroid.entities.Word
import org.jetbrains.anko.doAsync

//exportSchema pra evitar o warning

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private var instance: WordRoomDatabase? = null

        private val roomDatabaseCallback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                instance?.let {
                    doAsync {
                        val dao = it.wordDao()

                        dao.deleteAll()

//                        val word1 = Word("PagSeguro")
//                        val word2 = Word("Kotlin")
//
//                        dao.insert(word1)
//                        dao.insert(word2)


                    }
                }
            }
        }

        fun getDatabase(context: Context): WordRoomDatabase {
            if(instance == null) {
                synchronized(WordRoomDatabase::class.java) {
                    instance = Room.databaseBuilder(
                            //contexto global
                            context.applicationContext,
                            WordRoomDatabase::class.java,
                            "word_database"
                    )
//                            .addCallback(roomDatabaseCallback)
                            .build()
                }
            }

            //forçar nao ser null
            return instance!!
        }
    }
}
package com.example.estudoandroid.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
//@Entity(tableName = "word_table", indices = arrayOf(Index(unique = true)))
data class Word(
        @PrimaryKey(autoGenerate = true)

        var id: Long?,
        var word: String
) {
        constructor():this(null, "")
        constructor(extra: String?) : this()
}
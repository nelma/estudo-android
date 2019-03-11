package com.example.estudoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val kotlin = ProgrammingLanguage(
                R.drawable.ic_developer_board,
                "Kotlin",
                2010,
                "Kotlin Description"
        )

        val items = listOf(kotlin, kotlin)

        val adapter = ProgrammingLanguageAdapter(items) {
            longToast(it.title)
        }
        recyclerView.adapter = adapter

        alert("Message", "Title") {
            yesButton {
                toast("OK")
            }
            cancelButton {
                toast("Cancel")
            }
        }.show()

//        doAsync {
//
//        }
    }
}

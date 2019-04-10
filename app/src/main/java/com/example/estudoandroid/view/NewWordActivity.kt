package com.example.estudoandroid.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.estudoandroid.R
import kotlinx.android.synthetic.main.activity_new_word.*
import org.jetbrains.anko.longToast

class NewWordActivity : AppCompatActivity() {

    companion object {
        const val WORD_TEXT = "WORD"
        const val WORD_ID = "WORD_ID"
    }

    private var idWord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        intent.getStringExtra("WORD")?.let {
            val word = intent.getStringExtra("WORD")
            val id = intent.getStringExtra("WORD_ID")
            idWord = id
            longToast("$word")
            longToast("$id")
            etWord.setText(word)
        }


        btSave.setOnClickListener {

            val replyIntent = Intent()
            if(etWord.text.isEmpty()) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(WORD_TEXT, etWord.text.toString())
                replyIntent.putExtra(WORD_ID, idWord)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }
}

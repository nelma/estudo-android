package com.example.estudoandroid.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estudoandroid.R
import com.example.estudoandroid.entities.Word
import com.example.estudoandroid.viewmodel.WordViewModel

import kotlinx.android.synthetic.main.activity_basic.*
import kotlinx.android.synthetic.main.content_basic.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivityForResult

class BasicActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by lazy {
        ViewModelProviders.of(this).get(WordViewModel::class.java)
    }

    companion object {
        private const val NEW_WORD_REQUEST_CODE: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        setSupportActionBar(toolbar)


        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this, Observer {
            adapter.items = it
        })


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        startActivityForResult<NewWordActivity>(NEW_WORD_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_WORD_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                data?.let {
                    val word = Word(it.getStringExtra(NewWordActivity.WORD_KEY))
                    wordViewModel.insert(word)
                }

            } else {
                longToast("Word was empty")

//                fab.setOnClickListener{ view ->
//                    Snackbar.make(this, object , Snackbar.LENGTH_LONG)
//                            .setAction(fab.onClick {
//                                startActivityForResult<NewWordActivity>(NEW_WORD_REQUEST_CODE)
//                            }, null).show()
//                }
            }
        }
    }

}

package com.example.estudoandroid.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estudoandroid.R
import com.example.estudoandroid.adapter.ProgrammingLanguageAdapter
import com.example.estudoandroid.adapter.RepositoryAdapter
import com.example.estudoandroid.api.GithubRepositoriesResult
import com.example.estudoandroid.api.RepositoryRetriever
import com.example.estudoandroid.domain.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    //nao precisa do contexto, por isso pode inicializar
    private val respositoryRetriever = RepositoryRetriever()

    private val callback = object : Callback<GithubRepositoriesResult> {
        override fun onFailure(call: Call<GithubRepositoriesResult>, t: Throwable) {
            longToast("Fail loading repositories.")

            Log.e("MainActivity", "Fail loading repositories", t)
        }

        override fun onResponse(call: Call<GithubRepositoriesResult>,
                                response: Response<GithubRepositoriesResult>) {

            longToast("Load finished.")

            if (response.isSuccessful) {
                response.body()?.repositories?.let {
                    recyclerView.adapter = RepositoryAdapter(it) { repository ->

                        //cada repo
                        longToast("Clicked item: ${repository.fullName}")
                    }
                }
            }
        }

    }

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
//            longToast(it.title)

            respositoryRetriever.getLanguageRepositories(callback, it.title)
        }
        recyclerView.adapter = adapter

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
    }
}

package com.example.estudoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableDouble
import com.example.estudoandroid.R
import com.example.estudoandroid.databinding.ActivityMainBinding
import com.example.estudoandroid.model.Game
import com.example.estudoandroid.utils.SetContentView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by SetContentView(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
//                this, R.layout.activity_main)

        val game = Game(
                "Donkey Kong",
                1981,
                "https://images-submarino.b2w.io/produtos/01/00/sku/31839/5/31839531_1SZ.jpg",
                4.0)

        binding.game = game

        tvRating.setOnClickListener {
            game.rating += 0.1
            longToast(game.rating.toString())
        }
    }
}

package com.example.estudoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Flowables
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

//import io.reactivex.Observable
//import io.reactivex.Observer
//import io.reactivex.disposables.Disposable
//import java.util.function.Consumer

class MainActivity : AppCompatActivity() {

    lateinit var disposable: Disposable

    override fun onDestroy() {
        super.onDestroy()

        disposable.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //debounce com tempo..roda numa thread separada

        val nameChangeObservale =
                RxTextView
                        .textChanges(etName)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        val yearChangeObservale =
                RxTextView
                        .textChanges(etYear)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        val imageUrlChangeObservale =
                RxTextView
                        .textChanges(etImageUrl)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        val ratingChangeObservale =
                RxTextView
                        .textChanges(etRating)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)


        disposable = Flowables.combineLatest(
                nameChangeObservale,
                yearChangeObservale,
                imageUrlChangeObservale

        ) { newName, newYear, newImageUrl ->
            val nameValid = newName.length > 3
            if (!nameValid) {
                etName.error = "Invalid Name"
            }

            val yearValid = newYear.length > 3
            if (!yearValid) {
                etYear.error = "Invalid Name"
            }

            val imageUrlValid = newImageUrl.length > 3
            if (!imageUrlValid) {
                etImageUrl.error = "Invalid Name"
            }

            nameValid && yearValid && imageUrlValid

        }.subscribe {
            btAdd.setBackgroundColor(
                    ContextCompat.getColor(
                            applicationContext,
                            if (it)
                                R.color.colorPrimary
                            else
                                android.R.color.darker_gray
                    )
            )
        }


//        val observable = Observable.just(1, 2, 3)
//
//        val observer = object : Observer<Int> {
//            override fun onComplete() {
//                println("onComplete")
//            }
//
//            override fun onSubscribe(d: Disposable) {
//                println("onSubscribe $d")
//            }
//
//            override fun onNext(t: Int) {
//                println("onNext $t")
//            }
//
//            override fun onError(e: Throwable) {
//                println("onError $e")
//            }
//        }
//
//        //ou
//        val consumer = object: Consumer<Int> {
//            override fun accept(t: Int) {
//                println("accept $t")
//            }
//        }
//
//        observable.subscribe(observer)
//
//        val subscribe = observable.subscribe(consumer)
    }
}

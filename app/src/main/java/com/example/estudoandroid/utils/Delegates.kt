package com.example.estudoandroid.utils

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.BaseObservable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KProperty

class SetContentView<out T: ViewDataBinding>(
        @LayoutRes
        private val layoutRes: Int
) {

    private var value: T? = null


    //como get e set
    //KProperty<Any> qquer tipo do kotlin
    operator fun getValue(thisRef: Activity, property: KProperty<*>): T {

        value = value ?: DataBindingUtil.setContentView(
                thisRef, layoutRes)

        //forçando pq tem ctz do tipo
        return value!!

    }
}

fun <R : BaseObservable, T : Any> bindable(
        value: T, bindingRes: Int): BindableDelegate<R, T> {
    return BindableDelegate(value, bindingRes)
}

class BindableDelegate<in R : BaseObservable, T : Any>(
        private var value: T, private val bindingEntry: Int) {
    operator fun getValue(thisRef: R, property: KProperty<*>): T = value

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        this.value = value
        thisRef.notifyPropertyChanged(bindingEntry)
    }
}
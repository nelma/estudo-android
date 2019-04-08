package com.example.estudoandroid.login

import android.content.Context
import com.example.estudoandroid.R
import com.example.estudoandroid.robot.BaseTestRobot

class LoginTestRobot(private val context: Context) : BaseTestRobot() {

    //apply retorna o mesmo contexto que foi chamado
    fun setEmail(email: String) = apply {
        fillEditText(R.id.etEmail, email)
    }

    fun setPassword(pass: String) = apply {
        fillEditText(R.id.etPassword, pass)
    }

    fun clickLogin() = apply {
        clickButton(R.id.btLogin)
    }

    fun matchErrorText(err: Int) = matchText(
            textView(android.R.id.message),
            context.getString(err)
    )

}

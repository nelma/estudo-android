package com.example.estudoandroid.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.estudoandroid.LoginActivity
import com.example.estudoandroid.R
import kotlinx.android.synthetic.main.activity_login.view.*
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val rule = ActivityTestRule(LoginActivity::class.java)
    lateinit var activity: LoginActivity

    lateinit var robot: LoginTestRobot

    @Before
    fun setup() {
        activity = rule.activity
        robot = LoginTestRobot(activity)
    }

    @Test
    fun loginSuccess() {
        robot
            .setEmail("admin@admin.com")
            .setPassword("admin")
            .clickLogin()
            .matchText(R.id.tvNameSurname, "Nome")
    }

    @Test
    fun loginMissingEmailPassword() {
        robot.clickLogin().matchErrorText(R.string.missing_fields)
    }

    @Test
    fun loginMissingPassword() {
        robot.setEmail("admin@admin.com").clickLogin().matchErrorText(R.string.missing_fields)
    }

    @Test
    fun loginWrongPassword() {
        robot.setEmail("admin@admin.com").setPassword("admin").clickLogin().matchErrorText(R.string.login_fail)
    }
}
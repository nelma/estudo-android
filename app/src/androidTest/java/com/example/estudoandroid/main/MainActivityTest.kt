package com.example.estudoandroid.main

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.estudoandroid.MainActivity
import com.example.estudoandroid.R
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)
    lateinit var activity: MainActivity

    @Before
    fun setup() {
        activity = rule.activity
    }

    @Test
    fun loadActivity_shouldShowHelloWord() {
        val viewById = activity.findViewById<TextView>(R.id.textView)
        Assert.assertThat(viewById, notNullValue())
        Assert.assertThat(viewById.text.toString(), equalTo("Hello World!"))
    }

    @Test
    fun sendButton_shouldWriteTypeText() {
        onView(withHint("Type some text")).perform(typeText("Nome"))
        onView(withText("Send")).perform(click())
        onView(withText("Hello, Nome!")).check(matches(isDisplayed()))
    }
}
package com.asos_codetest.spaceflightapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asos_codetest.spaceflightapp.R
import com.asos_codetest.spaceflightapp.view.adapter.FlightViewHolder
import org.junit.Before


import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Before
    fun setup(){
        launchFragmentInContainer(Bundle(), R.style.Theme_AppCompat) {
            HomeFragment()
        }
    }

    @Test
    fun test_isTitleTextViewVisible() {
        onView(
            withId(R.id.falcon_9_launches_textview)
        ).check(matches(isDisplayed()))
    }

    @Test
    fun test_isTitleTextViewCorrect(){
        onView(
            withId(R.id.falcon_9_launches_textview)
        ).check(matches(withText(R.string.falcon_9_launches_text)))
    }

    @Test
    fun test_isRecyclerViewVisible(){
        onView(
            withId(R.id.flight_data_recyclerview)
        ).check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerViewHasItem(){
        onView(
            withId(R.id.flight_data_recyclerview)
        ).perform(
            actionOnItemAtPosition<FlightViewHolder>(0, click())
        )
    }
}
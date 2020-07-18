package com.example.clean

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.clean.framework.adapter.LibraryVH
import com.example.clean.presentation.landing.MainActivity
import com.example.clean.presentation.library.LibraryViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentLibraryInstrumentationTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    private lateinit var libraryViewModel: LibraryViewModel

    @Before
    fun setUp(){
        libraryViewModel = mockk<LibraryViewModel>()
    }

    @Test
    fun testToolbarIsVisible(){
        rule.activity.runOnUiThread {
            rule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.libraryFragment)
        }

        onView(withId(R.id.toolBar)).check(matches(isDisplayed())).check(matches(hasDescendant(
            withText(R.string.menu_library)
        )))
    }

    @Test
    fun testRecycleView(){
        rule.activity.runOnUiThread {
            rule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.libraryFragment)
        }

        onView(withId(R.id.rv_library)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<LibraryVH>(0, click())
        )
    }

}
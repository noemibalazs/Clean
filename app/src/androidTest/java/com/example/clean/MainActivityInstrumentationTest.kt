package com.example.clean

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.clean.presentation.landing.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().context
        assertEquals("com.example.clean.test", context.packageName)
    }

    @Test
    fun testNavigationViewIsDisplayed() {
        rule.activity.runOnUiThread {
            rule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.libraryFragment)
        }

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click())
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavControllerIsVisible() {
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun testToolbar() {
        rule.activity.runOnUiThread {
            rule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.libraryFragment)
        }

        onView(withId(R.id.toolBar)).check(matches(isDisplayed())).check(
            matches(
                hasDescendant(
                    withText(R.string.menu_library)
                )
            )
        )
    }

    @Test
    fun testDrawerLayoutIsDisplayed() {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
    }
}
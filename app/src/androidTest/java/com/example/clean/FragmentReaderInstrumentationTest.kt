package com.example.clean

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.clean.presentation.landing.MainActivity
import com.example.clean.presentation.reader.ReaderViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentReaderInstrumentationTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    private lateinit var readerViewModel: ReaderViewModel

    @Before
    fun setUp() {
        readerViewModel = mockk<ReaderViewModel>()
    }

    @Test
    fun testToolbar(){
        rule.activity.runOnUiThread {
            rule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.readerFragment)
        }

        onView(withId(R.id.toolBar)).check(matches(isDisplayed())).check(matches(
            hasDescendant(withText(R.string.menu_pdf_reader))
        ))
    }

    @Test
    fun testWidgetsAreDisplayed(){
        rule.activity.runOnUiThread {
            rule.activity.findNavController(R.id.nav_host_fragment).navigate(R.id.readerFragment)
        }

        onView(withId(R.id.pv_page_reader)).check(matches(isDisplayed()))
        onView(withId(R.id.cl_bottom)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_page_previous)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_page_next)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_reader_page_number)).check(matches(isDisplayed()))
    }
}
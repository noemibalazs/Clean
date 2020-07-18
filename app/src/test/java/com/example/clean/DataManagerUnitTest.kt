package com.example.clean

import com.example.clean.framework.action.DataManager
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest = Config.NONE)
class DataManagerUnitTest {

    private var dataManager: DataManager? = null

    @Before
    fun setUp() {
        dataManager = DataManager(RuntimeEnvironment.application.applicationContext)
    }

    @Test
    fun testSaveGetDocUrlShouldPass() {
        dataManager?.saveDocumentUrl("URL")
        val url = dataManager?.getDocumentUrl()
        assertEquals(url, "URL")
    }

    @Test
    fun testSaveGetDocUrlShouldFail() {
        dataManager?.saveDocumentUrl("URL")
        val url = dataManager?.getDocumentUrl()
        assertNotEquals(url, "LRU")
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}
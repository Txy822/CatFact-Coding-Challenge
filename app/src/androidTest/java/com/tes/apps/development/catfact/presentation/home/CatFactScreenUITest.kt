package com.tes.apps.development.catfact.presentation.home

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.tes.apps.development.catfact.presentation.ui.CatFactApp
import com.tes.apps.development.catfact.presentation.ui.MainActivity

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CatFactScreenEndToEndTest {

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent {
            CatFactApp()
        }
    }

    @Test
    fun nodeTextsOfSearchTest() {
        composeRule.onAllNodesWithText("Search breeds abys, beng etc...", useUnmergedTree = true)
            .assertCountEquals(2)
    }

    @Test
    fun checkSearchInputTest() {
        composeRule.onNodeWithText("Search breeds abys, beng etc...")
            .performTextInput("Abyssinian")
    }
    @After
    fun tearDown() {
    }
}
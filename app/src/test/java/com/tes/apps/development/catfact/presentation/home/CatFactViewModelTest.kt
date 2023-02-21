package com.tes.apps.development.catfact.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.tes.apps.development.catfact.domain.repository.CatFactRepository
import com.tes.apps.development.catfact.domain.use_case.CatFactUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CatFactViewModelTest {

    @get:Rule
    val instantTaskExecutionRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatFactRepository

    private val testDispatcher = StandardTestDispatcher()

    private lateinit  var catFactUseCase: CatFactUseCase

    private lateinit var viewModel: CatFactViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        catFactUseCase= CatFactUseCase(repository)
        viewModel=CatFactViewModel( testDispatcher, catFactUseCase)
    }

    @Test
    fun `check viewModel initial state`() {
        runTest {
            val expectedState = CatFactUiState()
            val updatedState = viewModel.uiState.value
            assertEquals(expectedState, updatedState)
        }
    }
    @Test
    fun `check viewModel updated state`() {
        runTest {
            val expectedState = CatFactUiState(isLoading = false, searchQuery = "absy")
            viewModel.onEvent(CatFactEvent.OnSearchQueryChange("absy"))
            // Await the change
            testDispatcher.scheduler.advanceUntilIdle()
            val updatedState = viewModel.uiState.value
            assertEquals(expectedState, updatedState)
        }
    }

    @Test
    fun `Success state updates value `() {
        runTest {
            val expectedState = CatFactUiState(isLoading = false, searchQuery = "Aegean\n")
            viewModel.onEvent(CatFactEvent.OnSearchQueryChange("Aegean\n"))
            viewModel.uiState.test {
                assertEquals( expectedState, awaitItem()) //emit  data
            }
        }
    }

    @After
    fun tearDown() {
    }
}
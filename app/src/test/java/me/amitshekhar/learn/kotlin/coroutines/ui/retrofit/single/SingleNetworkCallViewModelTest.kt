package me.amitshekhar.learn.kotlin.coroutines.ui.retrofit.single

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelper
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelper
import me.amitshekhar.learn.kotlin.coroutines.data.model.ApiUser
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState
import me.amitshekhar.learn.kotlin.coroutines.utils.TestCoroutineRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var databaseHelper: DatabaseHelper

    @Mock
    private lateinit var uiStateObserver: Observer<UiState<List<ApiUser>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<ApiUser>())
                .`when`(apiHelper)
                .getUsers()
            val viewModel = SingleNetworkCallViewModel(apiHelper, databaseHelper)
            viewModel.getUiState().observeForever(uiStateObserver)
            verify(apiHelper).getUsers()
            verify(uiStateObserver).onChanged(UiState.Success(emptyList()))
            viewModel.getUiState().removeObserver(uiStateObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getUsers()
            val viewModel = SingleNetworkCallViewModel(apiHelper, databaseHelper)
            viewModel.getUiState().observeForever(uiStateObserver)
            verify(apiHelper).getUsers()
            verify(uiStateObserver).onChanged(
                UiState.Error(RuntimeException(errorMessage).toString())
            )
            viewModel.getUiState().removeObserver(uiStateObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}
package me.amitshekhar.learn.kotlin.coroutines.ui.task.onetask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelper
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelper
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState

class LongRunningTaskViewModel(
    private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<String>>()

    fun startLongRunningTask() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                // do a long running task
                doLongRunningTask()
                uiState.postValue(UiState.Success("Task Completed"))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUiState(): LiveData<UiState<String>> {
        return uiState
    }

    private suspend fun doLongRunningTask() {
        withContext(Dispatchers.Default) {
            // your code for doing a long running task
            // Added delay to simulate
            delay(5000)
        }
    }

}
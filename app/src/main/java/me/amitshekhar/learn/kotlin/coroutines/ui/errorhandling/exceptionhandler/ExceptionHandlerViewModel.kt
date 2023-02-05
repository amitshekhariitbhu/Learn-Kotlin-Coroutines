package me.amitshekhar.learn.kotlin.coroutines.ui.errorhandling.exceptionhandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelper
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelper
import me.amitshekhar.learn.kotlin.coroutines.data.model.ApiUser
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState

class ExceptionHandlerViewModel(
    private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        uiState.postValue(UiState.Error("exception handler: $e"))
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(exceptionHandler) {
            uiState.postValue(UiState.Loading)
            val usersFromApi = apiHelper.getUsersWithError()
            uiState.postValue(UiState.Success(usersFromApi))
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}
package me.amitshekhar.learn.kotlin.coroutines.ui.timeout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelper
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelper
import me.amitshekhar.learn.kotlin.coroutines.data.model.ApiUser
import me.amitshekhar.learn.kotlin.coroutines.utils.UiState

class TimeoutViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<UiState<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(UiState.Loading)
            try {
                withTimeout(100) {
                    val usersFromApi = apiHelper.getUsers()
                    users.postValue(UiState.Success(usersFromApi))
                }
            } catch (e: TimeoutCancellationException) {
                users.postValue(UiState.Error("TimeoutCancellationException"))
            } catch (e: Exception) {
                users.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUsers(): LiveData<UiState<List<ApiUser>>> {
        return users
    }

}
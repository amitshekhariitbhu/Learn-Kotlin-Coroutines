package me.amitshekhar.learn.kotlin.coroutines.ui.errorhandling.supervisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelper
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelper
import me.amitshekhar.learn.kotlin.coroutines.data.model.ApiUser
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState

class IgnoreErrorAndContinueViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            // supervisorScope is needed, so that we can ignore error and continue
            // here, more than two child jobs are running in parallel under a supervisor, one child job gets failed, we can continue with other.
            supervisorScope {
                val usersFromApiDeferred = async { apiHelper.getUsersWithError() }
                val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }

                val usersFromApi = try {
                    usersFromApiDeferred.await()
                } catch (e: Exception) {
                    emptyList()
                }

                val moreUsersFromApi = try {
                    moreUsersFromApiDeferred.await()
                } catch (e: Exception) {
                    emptyList()
                }

                val allUsersFromApi = mutableListOf<ApiUser>()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)

                uiState.postValue(UiState.Success(allUsersFromApi))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}
package me.amitshekhar.learn.kotlin.coroutines.ui.task.onetask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_long_running_task.*
import kotlinx.android.synthetic.main.activity_recycler_view.progressBar
import me.amitshekhar.learn.kotlin.coroutines.R
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelperImpl
import me.amitshekhar.learn.kotlin.coroutines.data.api.RetrofitBuilder
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseBuilder
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelperImpl
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState
import me.amitshekhar.learn.kotlin.coroutines.ui.base.ViewModelFactory

class LongRunningTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: LongRunningTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_running_task)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    textView.text = it.data
                    textView.visibility = View.VISIBLE
                }
                is UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                }
                is UiState.Error -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.startLongRunningTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        )[LongRunningTaskViewModel::class.java]
    }

}

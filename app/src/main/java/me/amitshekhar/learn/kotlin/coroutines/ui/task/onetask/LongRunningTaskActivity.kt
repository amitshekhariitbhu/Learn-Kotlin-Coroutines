package me.amitshekhar.learn.kotlin.coroutines.ui.task.onetask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelperImpl
import me.amitshekhar.learn.kotlin.coroutines.data.api.RetrofitBuilder
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseBuilder
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelperImpl
import me.amitshekhar.learn.kotlin.coroutines.databinding.ActivityLongRunningTaskBinding
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState
import me.amitshekhar.learn.kotlin.coroutines.ui.base.ViewModelFactory

class LongRunningTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: LongRunningTaskViewModel
    private lateinit var binding: ActivityLongRunningTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLongRunningTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textView.text = it.data
                    binding.textView.visibility = View.VISIBLE
                }
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textView.visibility = View.GONE
                }
                is UiState.Error -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
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

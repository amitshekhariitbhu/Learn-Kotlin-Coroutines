package me.amitshekhar.learn.kotlin.coroutines.ui.room

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*
import me.amitshekhar.learn.kotlin.coroutines.R
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelperImpl
import me.amitshekhar.learn.kotlin.coroutines.data.api.RetrofitBuilder
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseBuilder
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelperImpl
import me.amitshekhar.learn.kotlin.coroutines.data.local.entity.User
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UserAdapter
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState
import me.amitshekhar.learn.kotlin.coroutines.ui.base.ViewModelFactory

class RoomDBActivity : AppCompatActivity() {

    private lateinit var viewModel: RoomDBViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            UserAdapter(
                arrayListOf()
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    renderList(it.data)
                    recyclerView.visibility = View.VISIBLE
                }
                is UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                is UiState.Error -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        )[RoomDBViewModel::class.java]
    }
}

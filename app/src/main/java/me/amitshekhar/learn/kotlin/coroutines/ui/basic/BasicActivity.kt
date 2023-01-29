package me.amitshekhar.learn.kotlin.coroutines.ui.basic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import me.amitshekhar.learn.kotlin.coroutines.R

class BasicActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "BasicActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }

    fun testCoroutine(view: View) {
        testCoroutine()
    }

    fun testCoroutineWithMain(view: View) {
        testCoroutineWithMain()
    }

    fun testCoroutineWithMainImmediate(view: View) {
        testCoroutineWithMainImmediate()
    }

    fun testCoroutineEverything(view: View) {
        testCoroutineEverything()
    }

    fun usingGlobalScope(view: View) {
        usingGlobalScope()
    }

    private fun testCoroutine() {
        Log.d(TAG, "Function Start")

        lifecycleScope.launch {
            Log.d(TAG, "Before Task")
            doLongRunningTask()
            Log.d(TAG, "After Task")
        }

        Log.d(TAG, "Function End")
    }

    private fun testCoroutineWithMain() {
        Log.d(TAG, "Function Start")

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Before Task")
            doLongRunningTask()
            Log.d(TAG, "After Task")
        }

        Log.d(TAG, "Function End")
    }

    // similar to #testCoroutine
    private fun testCoroutineWithMainImmediate() {
        Log.d(TAG, "Function Start")

        lifecycleScope.launch(Dispatchers.Main.immediate) {
            Log.d(TAG, "Before Task")
            doLongRunningTask()
            Log.d(TAG, "After Task")
        }

        Log.d(TAG, "Function End")
    }

    private fun testCoroutineEverything() {
        Log.d(TAG, "Function Start")

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Before Task 1")
            doLongRunningTask()
            Log.d(TAG, "After Task 1")
        }

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Before Task 2")
            doLongRunningTask()
            Log.d(TAG, "After Task 2")
        }

        Log.d(TAG, "Function End")
    }

    // NOTE: NEVER USE GlobalScope, I have used it for the educational purpose only.
    private fun usingGlobalScope() {
        Log.d(TAG, "Function Start")
        GlobalScope.launch {
            Log.d(TAG, "Before Task")
            doLongRunningTask()
            Log.d(TAG, "After Task")
        }
        Log.d(TAG, "Function End")
    }

    private suspend fun doLongRunningTask() {
        withContext(Dispatchers.Default) {
            // your code for doing a long running task
            // Added delay to simulate
            delay(2000)
        }
    }

}
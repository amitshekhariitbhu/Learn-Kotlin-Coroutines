package me.amitshekhar.learn.kotlin.coroutines.ui.basic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import me.amitshekhar.learn.kotlin.coroutines.R

class BasicActivity : AppCompatActivity() {

    private val myActivityScope = CoroutineScope(Dispatchers.Main)

    companion object {
        private const val TAG = "BasicActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }

    override fun onDestroy() {
        super.onDestroy()
        myActivityScope.cancel()
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

    fun usingMyActivityScope(view: View) {
        usingMyActivityScope()
    }

    fun twoTasks(view: View) {
        twoTasks()
    }

    fun parentAndChildTaskCancel(view: View) {
        parentAndChildTaskCancel()
    }

    fun parentAndChildTaskCancelIsActive(view: View) {
        parentAndChildTaskCancelIsActive()
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

    private fun usingMyActivityScope() {
        Log.d(TAG, "Function Start")
        myActivityScope.launch {
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

    private fun twoTasks() {
        Log.d(TAG, "Function Start")

        val job = lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Before Task 1")
            doLongRunningTask()
            Log.d(TAG, "After Task 1")
        }

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Before Task 2")
            job.cancel()
            doLongRunningTask()
            Log.d(TAG, "After Task 2")
        }

        Log.d(TAG, "Function End")
    }

    private fun parentAndChildTaskCancel() {
        Log.d(TAG, "Function Start")

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Before Task")
            childTask(coroutineContext[Job]!!)
            Log.d(TAG, "After Task")
        }

        Log.d(TAG, "Function End")
    }

    private suspend fun childTask(parent: Job) {
        withContext(Dispatchers.Default) {
            Log.d(TAG, "childTask start")
            parent.cancel()
            Log.d(TAG, "childTask parent cancel")
            // your code for doing a long running task
            // Added delay to simulate
            delay(2000)
            Log.d(TAG, "childTask end")
        }
    }

    private fun parentAndChildTaskCancelIsActive() {
        Log.d(TAG, "Function Start")

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Before Task")
            childTaskWithIsActive(coroutineContext[Job]!!)
            Log.d(TAG, "After Task")
        }

        Log.d(TAG, "Function End")
    }

    private suspend fun childTaskWithIsActive(parent: Job) {
        withContext(Dispatchers.Default) {
            Log.d(TAG, "childTask start")
            parent.cancel()
            if (isActive) {
                Log.d(TAG, "childTask parent cancel")
            }
            // your code for doing a long running task
            // Added delay to simulate
            delay(2000)
            Log.d(TAG, "childTask end")
        }
    }

    private fun learnLaunch() {
        lifecycleScope.launch(Dispatchers.Default) {
            // do something
        }
    }

    private fun learnLaunchWithJob() {
        val job = lifecycleScope.launch(Dispatchers.Default) {
            // do something
        }
    }

    private suspend fun learnAsync() {
        val deferred = lifecycleScope.async(Dispatchers.Default) {
            // do something
            return@async 10
        }
        val result = deferred.await()
    }

    private suspend fun asyncWithReturn(): Int {
        return lifecycleScope.async(Dispatchers.Default) {
            // do something
            return@async 10
        }.await()
    }

    private suspend fun learnWithContext() {
        val result = withContext(Dispatchers.Default) {
            // do something
            return@withContext 10
        }
    }

    private suspend fun withContextWithReturn(): Int {
        return withContext(Dispatchers.Default) {
            // do something
            return@withContext 10
        }
    }

}
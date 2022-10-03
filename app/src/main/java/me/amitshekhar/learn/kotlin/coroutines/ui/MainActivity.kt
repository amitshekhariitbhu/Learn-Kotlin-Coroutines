package me.amitshekhar.learn.kotlin.coroutines.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.amitshekhar.learn.kotlin.coroutines.R
import me.amitshekhar.learn.kotlin.coroutines.ui.errorhandling.exceptionhandler.ExceptionHandlerActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.errorhandling.supervisor.IgnoreErrorAndContinueActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.errorhandling.trycatch.TryCatchActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.retrofit.parallel.ParallelNetworkCallsActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.retrofit.series.SeriesNetworkCallsActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.retrofit.single.SingleNetworkCallActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.room.RoomDBActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.task.onetask.LongRunningTaskActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.task.twotasks.TwoLongRunningTasksActivity
import me.amitshekhar.learn.kotlin.coroutines.ui.timeout.TimeoutActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
    }

    fun startSeriesNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
    }

    fun startParallelNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
    }

    fun startRoomDatabaseActivity(view: View) {
        startActivity(Intent(this@MainActivity, RoomDBActivity::class.java))
    }

    fun startTimeoutActivity(view: View) {
        startActivity(Intent(this@MainActivity, TimeoutActivity::class.java))
    }

    fun startTryCatchActivity(view: View) {
        startActivity(Intent(this@MainActivity, TryCatchActivity::class.java))
    }

    fun startExceptionHandlerActivity(view: View) {
        startActivity(Intent(this@MainActivity, ExceptionHandlerActivity::class.java))
    }

    fun startIgnoreErrorAndContinueActivity(view: View) {
        startActivity(Intent(this@MainActivity, IgnoreErrorAndContinueActivity::class.java))
    }

    fun startLongRunningTaskActivity(view: View) {
        startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
    }

    fun startTwoLongRunningTasksActivity(view: View) {
        startActivity(Intent(this@MainActivity, TwoLongRunningTasksActivity::class.java))
    }

}

package com.example.continuewatch_2

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import android.os.Handler

class Main_ExecutionService: AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var handler: Handler
    private lateinit var executor:ExecutorService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.getMainLooper())
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onResume() {
        executor = For_Exec().executor
        executor.execute(object : Runnable {
            override fun run() {
                if (!executor.isShutdown) {
                    textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            getString(R.string.secondsE, secondsElapsed++)
                    }
                    handler.postDelayed(this, 1000)
                }
            }
        })
        super.onResume()
    }

    override fun onStop() {//right
        super.onStop()
        Log.d("mainActivity", "OnStop: seconds = $secondsElapsed")
        executor.shutdown()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(getString(R.string.secondsE), secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(getString(R.string.secondsE))
        super.onRestoreInstanceState(savedInstanceState)
    }
}
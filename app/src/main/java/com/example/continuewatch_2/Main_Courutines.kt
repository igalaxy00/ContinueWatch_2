package com.example.continuewatch_2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.*

class Main_Courutines : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (isActive) {
                    textSecondsElapsed.text = getString(R.string.secondsC, ++secondsElapsed)
                    delay(1000)
                }
        }
        }
    }

    override fun onStop() {//right
        super.onStop()

    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            Log.d("mainActivity", "Saving state seconds =$secondsElapsed")
            putInt(SECONDS, secondsElapsed)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            secondsElapsed = getInt(SECONDS)
            Log.d("mainActivity", "Restore state seconds =$secondsElapsed")
        }
    }


    companion object {
        const val SECONDS = "Seconds"
    }


}
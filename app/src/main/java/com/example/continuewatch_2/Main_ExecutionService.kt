package com.example.continuewatch_2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.continuewatch_2.databinding.ActivityMain2Binding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future


class Main_ExecutionService: AppCompatActivity() {

    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private lateinit var backgroundFuture: Future<*>

    private fun submitBackground(executorService: ExecutorService) = executorService.submit {
        while (!executorService.isShutdown) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                textSecondsElapsed.text = getString(R.string.secondsE, ++secondsElapsed)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        textSecondsElapsed = binding.textSecondsElapsed2
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        backgroundFuture = submitBackground((applicationContext as For_Exec).executor)
    }

    override fun onStop() {
        super.onStop()
        backgroundFuture.cancel(true)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(SEC, secondsElapsed)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            secondsElapsed = getInt(SEC)
        }
    }

    companion object {
        const val SEC = "secondsElapsed"
    }
}
package com.example.continuewatch_2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
class Main_ExecutionService: AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var executor: ExecutorService


    override fun onStart() {//right
        super.onStart()
        executor = Executors.newFixedThreadPool(1)
        Log.d("mainActivity", "OnStart: seconds = $secondsElapsed")
        executor.execute {
            while(!executor.isShutdown) {
                try {
                    Log.d("mainActivity", "${Thread.currentThread()} is iterating")
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text = getString(R.string.secondsE, secondsElapsed++)
                    }
                }catch (e: Exception){}
            }
        }
    }

    override fun onStop() {//right
        super.onStop()
        Log.d("mainActivity", "OnStop: seconds = $secondsElapsed")
        executor.shutdown()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putInt(SECONDS, secondsElapsed) }
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run { secondsElapsed = getInt(SECONDS) }
    }


    companion object {
        const val SECONDS = "Seconds"
    }



}
